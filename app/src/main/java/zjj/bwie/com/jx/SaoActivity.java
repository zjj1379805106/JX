package zjj.bwie.com.jx;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class SaoActivity extends Activity implements View.OnClickListener {

    private Button btn_sao;
    private EditText et_sao_sc;
    private Button btn_sao_sc;
    private ImageView img_sao_sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao);

        btn_sao = findViewById(R.id.btn_sao);
        et_sao_sc = findViewById(R.id.et_sao_sc);
        btn_sao_sc = findViewById(R.id.btn_sao_sc);
        img_sao_sc = findViewById(R.id.img_sao_sc);

        btn_sao.setOnClickListener(this);
        btn_sao_sc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sao:
                startActivity(new Intent(this,CaptureActivity.class));
                break;
            case R.id.btn_sao_sc:
                String s = et_sao_sc.getText().toString();
                if(TextUtils.isEmpty(s)){
                    Toast.makeText(this, "请输入文字", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap image = CodeUtils.createImage(s, 250, 250, BitmapFactory.decodeResource(getResources(), R.drawable.mqq));
                img_sao_sc.setImageBitmap(image);
                break;
        }
    }
}
