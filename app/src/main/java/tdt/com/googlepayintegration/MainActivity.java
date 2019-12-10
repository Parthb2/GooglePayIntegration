package tdt.com.googlepayintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mAmount;
    private EditText mName;
    private EditText mUpiId;
    private EditText mNote;
    private Button mPay;
    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaration
        mAmount = (EditText)findViewById(R.id.amount_et);
        mName = (EditText)findViewById(R.id.name);
        mUpiId = (EditText)findViewById(R.id.upi_id);
        mNote = (EditText)findViewById(R.id.note);

        mPay = (Button) findViewById(R.id.send);

        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting the values from the EditTexts
                if (TextUtils.isEmpty(mName.getText().toString().trim())){
                    Toast.makeText(MainActivity.this," Receivers Name is invalid", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(mUpiId.getText().toString().trim())){
                    Toast.makeText(MainActivity.this," UPI ID is invalid", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(mNote.getText().toString().trim())){
                    Toast.makeText(MainActivity.this," Note is invalid", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(mAmount.getText().toString().trim())){
                    Toast.makeText(MainActivity.this," Amount is invalid", Toast.LENGTH_SHORT).show();
                }else{
                    payUsingUpi(mNote.getText().toString(), mUpiId.getText().toString(),
                            mNote.getText().toString(), mAmount.getText().toString());
                }

            }
        });

    }

    void payUsingUpi(  String name,String upiId, String note, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);


    }
}
