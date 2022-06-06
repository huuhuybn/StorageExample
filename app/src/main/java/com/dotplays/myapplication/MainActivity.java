package com.dotplays.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ghiVaoBoNhoCache(View view) {
        try {
            String data = "Thu ghi vao cache"; // bien string dung de luu thu
            String nameFile = "abc.cache"; // tên file cache
            File cacheFileDir = getCacheDir(); // lấy vị trí thư muc cache 
            File cacheFile = new File(cacheFileDir, nameFile); // tạo file mới tại vị trí thư mục cache vs tên là : abc.cache
            cacheFile.createNewFile(); // tạo file
            FileOutputStream stream = new FileOutputStream(cacheFile); // mở kết nối vào file để tiến hành ghi 
            stream.write(data.getBytes()); // ghi dữ liệu từ data vào file cache  dạng byte
            stream.close(); // đóng kết nối 
            Toast.makeText(this, "Ghi Thành Công!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    public void docTuBoNhoCache(View view) {
        try {
            String nameFile = "abc.cache"; // tên file cache
            File cacheFileDir = getCacheDir(); // lấy vị trí thư muc cache
            File cacheFile = new File(cacheFileDir, nameFile); // tạo file mới tại vị trí thư mục cache vs tên là : abc.cache
            FileInputStream inputStream = new FileInputStream(cacheFile.getAbsolutePath());
            Scanner scanner = new Scanner(inputStream);
            String duLieu = "";
            while (scanner.hasNext()) {
                duLieu = duLieu + scanner.nextLine();
            }
            scanner.close();
            Toast.makeText(this, duLieu, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }

    }

    public void ghiVaoBoNhoTrong(View view) {
        try {
            String data = "Test thu thoi nhe";
            String nameFile = "xinxin.txt";
            FileOutputStream stream = openFileOutput(nameFile, MODE_PRIVATE);
            stream.write(data.getBytes());
            stream.close();
        } catch (Exception e) {

        }
    }

    public void docTuBoNhoTrong(View view) {
        try {
            String nameFile = "xinxin.txt";
            FileInputStream stream = openFileInput(nameFile);
            // doc bang Scanner
            Scanner scanner = new Scanner(stream);
            String duLieu = "";
            while (scanner.hasNext()) {
                duLieu = duLieu + scanner.nextLine();
            }
            scanner.close();
            Toast.makeText(this, duLieu, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

        }
    }

    public void docTuBoNhoSharePref(View view) {
        SharedPreferences preferences = getSharedPreferences("test.txt", MODE_PRIVATE);
        String name = preferences.getString("name", null);
        int tuoi = preferences.getInt("tuoi", -1);
        boolean gioiTinh = preferences.getBoolean("gioiTinh", false);
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(" - ");
        builder.append(tuoi);
        builder.append(" - ");
        builder.append(gioiTinh);
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();

    }

    public void ghiVaoBoNhoSharePref(View view) {
        String name = "Huy Nguyen";
        int tuoi = 33;
        boolean gioiTinh = true;
        SharedPreferences preferences = getSharedPreferences("test.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("gioiTinh", gioiTinh);
        editor.putString("name", name);
        editor.putInt("tuoi", tuoi);
        editor.commit(); // == save

    }

    public void docTuBoNhoNgoai(View view) {
        // 1: kiem tra xin quyen ghi tu nguoi dung app
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
        } else {
            try {
                String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/file.txt";
                FileInputStream stream = new FileInputStream(path);
                String data = "";
                Scanner scanner = new Scanner(stream);
                String duLieu = "";
                while (scanner.hasNext()) {
                    duLieu = duLieu + scanner.nextLine();
                }
                scanner.close();
                Toast.makeText(this, duLieu, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {

            }
        }
    }

    public void ghiVaoBoNhoNgoai(View view) {
        // 1: kiem tra xin quyen ghi tu nguoi dung app
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    999);
        } else {
            try {
                String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/file.txt";
                FileOutputStream outputStream = new FileOutputStream(path);
                String data = "test thu vao the nho";
                outputStream.write(data.getBytes());
                outputStream.close();
            } catch (Exception e) {

            }
        }
    }
}