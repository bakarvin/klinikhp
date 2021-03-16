package com.bakarvin.klinikhp.admin.crud.rekmedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.adapter.PdfDocumentAdapter;
import com.bakarvin.klinikhp.admin.MainMenuStaffActivity;
import com.bakarvin.klinikhp.databinding.ActivityDetailRekamMedisStaffBinding;
import com.bakarvin.klinikhp.model.Common;
import com.bakarvin.klinikhp.model.RekamMedis;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailRekamMedisStaffActivity extends AppCompatActivity {

    ActivityDetailRekamMedisStaffBinding rekamMedisStaffBinding;
    DatabaseReference dbMedis;
    String getIdMedis;
    String getNamaDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rekamMedisStaffBinding = ActivityDetailRekamMedisStaffBinding.inflate(getLayoutInflater());
        setContentView(rekamMedisStaffBinding.getRoot());
        dbMedis = FirebaseDatabase.getInstance().getReference("RekamMedis");
        getIdMedis = getIntent().getStringExtra("idMedis");
        rekamMedisStaffBinding.btnBackDetailMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailRekamMedisStaffActivity.this, MainMenuStaffActivity.class));
            }
        });
        init(getIdMedis);
//        exportPdf();
    }

    private void init(String getIdMedis) {
        dbMedis.child(getIdMedis).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RekamMedis rekamMedis = snapshot.getValue(RekamMedis.class);
                rekamMedisStaffBinding.txtTitleDetailMedis.setText(rekamMedis.getId_Medis());
                rekamMedisStaffBinding.txtDetailIdRekamMedis.setText(rekamMedis.getId_Medis());
                rekamMedisStaffBinding.txtDetailIdDokterMedis.setText(rekamMedis.getId_dokter());
                rekamMedisStaffBinding.txtDetailIdPasienMedis.setText(rekamMedis.getId_pasien());
                rekamMedisStaffBinding.txtDetailNamaPasienMedis.setText(rekamMedis.getNama_pasien());
                rekamMedisStaffBinding.txtDetailAnasMedis.setText(rekamMedis.getAnastesa());
                rekamMedisStaffBinding.txtDetailDiagMedis.setText(rekamMedis.getDiagnosa());
                rekamMedisStaffBinding.txtDetailTerapiMedis.setText(rekamMedis.getTerapi());
                rekamMedisStaffBinding.txtDetailResepMedis.setText(rekamMedis.getResep());
                rekamMedisStaffBinding.txtDetailTanggalMedis.setText(rekamMedis.getTgl_medis());
                getNamaDokter = rekamMedis.getNama_dokter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void exportPdf(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        rekamMedisStaffBinding.btnPrintRekamMedis.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                createPDF(Common.getAppPath(DetailRekamMedisStaffActivity.this)+"test_pdf.pdf");
                                Toast.makeText(DetailRekamMedisStaffActivity.this, "test pencet", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                });
    }

    void createPDF(String path) {
        if (new File(path).exists())
            new File(path).delete();
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(path));

            doc.open();

            doc.setPageSize(PageSize.A4);
            doc.addCreationDate();
            doc.addAuthor("Baka");
            doc.addCreator("Arvin");
             BaseColor CA = new BaseColor(0, 153, 204, 255);
             float fontSize = 16.0f;
             float valueFontSize = 26.0f;

             BaseFont baseFont = BaseFont.createFont("res/font/roboto_black.ttf", "UTF-8", BaseFont.EMBEDDED);

                //Title
            Font titleFont = new Font(baseFont, 36.0f, Font.NORMAL, BaseColor.BLACK);
            addNewItem(doc, "Order Details", Element.ALIGN_CENTER, titleFont);

                //Body
            Font orderNumber = new Font(baseFont, fontSize, Font.NORMAL, CA);
            addNewItem(doc, "Order No:", Element.ALIGN_LEFT, orderNumber);

            addLineSeparator(doc);

            doc.close();
            printPDF();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

    private void printPDF() {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        try {
            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(DetailRekamMedisStaffActivity.this, Common.getAppPath(DetailRekamMedisStaffActivity.this)+"test_pdf.pdf");
            printManager.print("Document", printDocumentAdapter, new PrintAttributes.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addLineSeparator(Document doc) throws DocumentException {
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,0,68));
        addLineSpace(doc);
        doc.add(new Chunk(lineSeparator));
        addLineSpace(doc);
    }

    private void addLineSpace(Document doc) throws DocumentException {
        doc.add(new Paragraph(""));
    }

    private void addNewItem(Document doc, String text, int alignCenter, Font font) throws DocumentException {
        Chunk chunk = new Chunk(text, font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(alignCenter);
        doc.add(paragraph);
    }

}