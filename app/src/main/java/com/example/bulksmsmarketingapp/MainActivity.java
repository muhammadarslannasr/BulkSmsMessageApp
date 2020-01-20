package com.example.bulksmsmarketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Spinner districtPakistan_Id;
    private EditText large_textId;
    private Button sendbuttonId;
    String districtListSpinner;
    static ArrayList<Word> phoneNumbers;
    static ArrayList<String> phoneNumbersUse;
    private Button button_file_nameId;
    private TextView file_nameId;
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }
        phoneNumbers = new ArrayList<>();
        phoneNumbersUse = new ArrayList<>();

        file_nameId = (TextView) findViewById(R.id.file_nameId);
        button_file_nameId = (Button) findViewById(R.id.button_file_nameId);
        large_textId = (EditText) findViewById(R.id.large_textId);
        sendbuttonId = (Button) findViewById(R.id.sendbuttonId);
        districtPakistan_Id = (Spinner) findViewById(R.id.districtPakistan_Id);
        List<String> districtPakistanList = new ArrayList<>();
        districtPakistanList.add("Awaran");
        districtPakistanList.add("Barkhan");
        districtPakistanList.add("Bolan");
        districtPakistanList.add("Islamabad");
        districtPakistanList.add("Chagai");
        districtPakistanList.add("Dera Bugti");
        districtPakistanList.add("Gwadar");
        districtPakistanList.add("Harnai");
        districtPakistanList.add("Jafarabad");
        districtPakistanList.add("Jhal Magsi");
        districtPakistanList.add("Kalat");
        districtPakistanList.add("Kech");
        districtPakistanList.add("Kharan");
        districtPakistanList.add("Kohlu");
        districtPakistanList.add("Khuzdar");
        districtPakistanList.add("Killa Abdullah");
        districtPakistanList.add("Killa Saifullah");
        districtPakistanList.add("Lasbela");
        districtPakistanList.add("Loralai");
        districtPakistanList.add("Mastung");
        districtPakistanList.add("Musakhel");
        districtPakistanList.add("Nasirabad");
        districtPakistanList.add("Nushki");
        districtPakistanList.add("Panjgur");
        districtPakistanList.add("Pishin");
        districtPakistanList.add("Quetta");
        districtPakistanList.add("Sherani");
        districtPakistanList.add("Sibi");
        districtPakistanList.add("Washuk");
        districtPakistanList.add("Zhob");
        districtPakistanList.add("Ziarat");
        districtPakistanList.add("Abbottabad");
        districtPakistanList.add("Bannu");
        districtPakistanList.add("Battagram");
        districtPakistanList.add("Buner");
        districtPakistanList.add("Charsadda");
        districtPakistanList.add("Chitral");
        districtPakistanList.add("Dera Ismail Khan");
        districtPakistanList.add("Hangu");
        districtPakistanList.add("Haripur");
        districtPakistanList.add("Karak");
        districtPakistanList.add("Kohat");
        districtPakistanList.add("Lakki Marwat");
        districtPakistanList.add("Lower Kohistan");
        districtPakistanList.add("Upper Kohistan");
        districtPakistanList.add("Lower Dir");
        districtPakistanList.add("Malakand");
        districtPakistanList.add("Mansehra");
        districtPakistanList.add("Mardan");
        districtPakistanList.add("Nowshera");
        districtPakistanList.add("Peshawar");
        districtPakistanList.add("Shangla");
        districtPakistanList.add("Swabi");
        districtPakistanList.add("Swat");
        districtPakistanList.add("Tank");
        districtPakistanList.add("Torghar");
        districtPakistanList.add("Upper Dir");
        districtPakistanList.add("Attock");
        districtPakistanList.add("Bahawalnagar");
        districtPakistanList.add("Bahawalpur");
        districtPakistanList.add("Bhakkar");
        districtPakistanList.add("Chakwal");
        districtPakistanList.add("Chiniot");
        districtPakistanList.add("Dera Ghazi Khan");
        districtPakistanList.add("Faisalabad");
        districtPakistanList.add("Gujranwala");
        districtPakistanList.add("Gujrat");
        districtPakistanList.add("Hafizabad");
        districtPakistanList.add("Jhang");
        districtPakistanList.add("Jhelum");
        districtPakistanList.add("Kasur");
        districtPakistanList.add("Khanewal");
        districtPakistanList.add("Khushab");
        districtPakistanList.add("Lahore");
        districtPakistanList.add("Layyah");
        districtPakistanList.add("Lodhran");
        districtPakistanList.add("Mandi Bahauddin");
        districtPakistanList.add("Mianwali");
        districtPakistanList.add("Multan");
        districtPakistanList.add("Muzaffargarh");
        districtPakistanList.add("Narowal");
        districtPakistanList.add("Nankana Sahib");
        districtPakistanList.add("Okara");
        districtPakistanList.add("Pakpattan");
        districtPakistanList.add("Rahim Yar Khan");
        districtPakistanList.add("Rajanpur");
        districtPakistanList.add("Rawalpindi");
        districtPakistanList.add("Sahiwal");
        districtPakistanList.add("Sargodha");
        districtPakistanList.add("Sheikhupura");
        districtPakistanList.add("Sialkot");
        districtPakistanList.add("Toba Tek Singh");
        districtPakistanList.add("Vehari");
        districtPakistanList.add("Badin");
        districtPakistanList.add("Dadu");
        districtPakistanList.add("Ghotki");
        districtPakistanList.add("Hyderabad");
        districtPakistanList.add("Jacobabad");
        districtPakistanList.add("Jamshoro");
        districtPakistanList.add("KARACHI");
        districtPakistanList.add("Kashmore");
        districtPakistanList.add("Khairpur");
        districtPakistanList.add("Larkana");
        districtPakistanList.add("Matiari");
        districtPakistanList.add("Mirpurkhas");
        districtPakistanList.add("Naushahro Firoze");
        districtPakistanList.add("Shaheed Benazirabad");
        districtPakistanList.add("Kambar Shahdadkot");
        districtPakistanList.add("Sanghar");
        districtPakistanList.add("Shikarpur");
        districtPakistanList.add("Sukkur");
        districtPakistanList.add("Tando Allahyar");
        districtPakistanList.add("Tando Muhammad Khan");
        districtPakistanList.add("Tharparkar");
        districtPakistanList.add("Thatta");
        districtPakistanList.add("Umerkot");
        districtPakistanList.add("Bajaur");
        districtPakistanList.add("Khyber");
        districtPakistanList.add("Kurram");
        districtPakistanList.add("Mohmand");
        districtPakistanList.add("North Waziristan");
        districtPakistanList.add("Orakzai");
        districtPakistanList.add("South Waziristan");
        districtPakistanList.add("FR Bannu");
        districtPakistanList.add("FR Dera Ismail Khan");
        districtPakistanList.add("FR Kohat");
        districtPakistanList.add("FR Lakki Marwat");
        districtPakistanList.add("FR Peshawar");
        districtPakistanList.add("FR Tank");

        Collections.sort(districtPakistanList);
        final ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districtPakistanList);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtPakistan_Id.setAdapter(districtAdapter);
        districtPakistan_Id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                districtListSpinner = adapterView.getItemAtPosition(i).toString();
                //addList(districtListSpinner.toLowerCase().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button_file_nameId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(MainActivity.this)
                        .withRequestCode(1000)
                        .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                        .withFilterDirectories(true) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();

            }
        });

        sendbuttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNumbersUse.size() > 0) {
                    final String my_message = large_textId.getText().toString().trim();
                    Intent intent = new Intent(MainActivity.this, CounterActivity.class);
                    intent.putExtra("name", "default");
                    intent.putExtra("text", my_message);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Please Uplaod File", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            addList(filePath);
            Toast.makeText(this, filePath + "", Toast.LENGTH_SHORT).show();
            // Do anything with file
        }
    }

    private void addList(String filePath) {
        Uri uri = Uri.parse(filePath);
        file_nameId.setText(uri.getLastPathSegment());

        phoneNumbers = new ArrayList<>();
        try {
//            InputStream inputStream = this.openFileInput(filePath);//.openFileInput("config.txt");
            FileInputStream inputStream = new FileInputStream(new File(filePath));

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    // stringBuilder.append("0"+receiveString + "\n");

//                    if (receiveString.contains(".")||receiveString.contains("+")) {
//                        continue;
//                    }
//                    else if (receiveString.startsWith("0") || receiveString.startsWith("+") || receiveString.startsWith("3") ) {
//                        phoneNumbers.add(new Word(districtListSpinner, receiveString));
//                        phoneNumbersUse.add(receiveString);
//                    }
//                    else if (receiveString.startsWith("9")) {
//                        phoneNumbers.add(new Word(districtListSpinner, "+" + receiveString));
//                        phoneNumbersUse.add(receiveString);
//                    }

                    if (receiveString.contains(".") || receiveString.contains("+"))
                        continue;
                    else
                        phoneNumbers.add(new Word(districtListSpinner, "0" + receiveString));
                        phoneNumbersUse.add("0" + receiveString);
                    Log.d("ListData" , phoneNumbersUse.size()+"");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


