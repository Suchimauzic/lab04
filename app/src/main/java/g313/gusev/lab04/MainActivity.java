package g313.gusev.lab04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

// Работа выполнена Гусевым Сергеем

public class MainActivity extends AppCompatActivity {

    CheckBox[] chk = new CheckBox[4];
    EditText[] num = new EditText[4];
    EditText[] price = new EditText[4];
    RadioGroup rbGroup;
    RadioButton rbToast;
    RadioButton rbDialog;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc = findViewById(R.id.btnCalc);

        rbGroup = findViewById(R.id.radioGroup);
        rbToast = findViewById(R.id.rbToast);
        rbDialog = findViewById(R.id.rbDialog);

        //rbGroup.addView(rbToast);
        //rbGroup.addView(tbDialog);

        chk[0] = findViewById(R.id.checkApple);
        chk[1] = findViewById(R.id.checkStrawberry);
        chk[2] = findViewById(R.id.checkBlueberry);
        chk[3] = findViewById(R.id.checkPotatoes);

        num[0] = findViewById(R.id.tNumbApple);
        num[1] = findViewById(R.id.tNumbStrawberry);
        num[2] = findViewById(R.id.tNumbBlueberry);
        num[3] = findViewById(R.id.tNumbPotatoes);

        price[0] = findViewById(R.id.tNumbApplePrice);
        price[1] = findViewById(R.id.tNumbStrawberryPrice);
        price[2] = findViewById(R.id.tNumbBlueberryPrice);
        price[3] = findViewById(R.id.tNumbPotatoesPrice);
    }

    public void onCalc(View v)
    {
        float sum = 0.0f;
        String rep = "";
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            if (chk[i].isChecked())
            {
                int q;
                float cost;
                try
                {
                    q = Integer.parseInt(num[i].getText().toString());
                    cost = Float.parseFloat(price[i].getText().toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Ошибка ввода!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (q == 0 || cost == 0.0f)
                {
                    chk[i].setChecked(false);
                }
                else
                {
                    float val = q * cost;
                    sum += val;
                    rep += String.format("%d: %d x %s = %d x %.2f = %.2f\n", count, q, chk[i].getText().toString(), q, cost, val);
                    count++;
                }
            }
        }

        rep += String.format("total - %.2f", sum);

        if (rbToast.isChecked())
        {
            Toast.makeText(getApplicationContext(), rep, Toast.LENGTH_SHORT).show();
        }
        if (rbDialog.isChecked()) {
            //FragmentManager manager = getSupportFragmentManager();
            //DialogFragment dialogFragment = new DialogFragment();
            //dialogFragment.show(manager, "myDialog");
            //FragmentTransaction transaction = manager.beginTransaction();
            //dialogFragment.show(transaction, "dialog");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Output result");
            builder.setMessage(rep);
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
    }
}