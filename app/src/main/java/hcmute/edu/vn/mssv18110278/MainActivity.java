package hcmute.edu.vn.mssv18110278;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.Signup.SignUpActivity;
import hcmute.edu.vn.mssv18110278.Users.Admin.AddItemActivity;
import hcmute.edu.vn.mssv18110278.Users.User.HomeActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseInsertHelper;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.Entity.Roles;

public class   MainActivity extends AppCompatActivity {
    Button forget_password_btn,login_btn,sign_up_btn,view_as_guest_btn;
    int HaveCustomer=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        forget_password_btn=(Button)findViewById(R.id.forget_password_btn);
        login_btn=(Button)findViewById(R.id.login_btn);
        sign_up_btn=(Button)findViewById(R.id.sign_up_btn);
        view_as_guest_btn=findViewById(R.id.view_as_guest_btn);


        view_as_guest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), HomeActivity.class);
                User user =new User(-1,null,null,-1,null);
                intent.putExtra("user", (Parcelable) user);
                startActivity(intent);
            }
        });


        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), SignUpActivity.class);
                intent.putExtra("sign_up_display", getResources().getString(R.string.employee_sign_up));
                intent.putExtra("role", Roles.CUSTOMER.name());
                startActivity(intent);
            }
        });
        forget_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new LoginButtonController(this));
        startUp();
        if(HaveCustomer ==1)
        {
            startUp1();
        }

    }

    private void startUp() {





        int adminId = DatabaseSelectHelper.getRoleIdFromName(Roles.ADMIN.name(), this);
        int customerId = DatabaseSelectHelper.getRoleIdFromName(Roles.CUSTOMER.name(), this);
        if (adminId == -1) {
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.putExtra("sign_up_display", getResources().getString(R.string.admin_sign_up));
            intent.putExtra("role", Roles.ADMIN.name());
            startActivity(intent);
        } else if (customerId == -1) {
            HaveCustomer =1;
            Intent intent = new Intent(this, SignUpActivity.class);
            intent.putExtra("sign_up_display", getResources().getString(R.string.employee_sign_up));
            intent.putExtra("role", Roles.CUSTOMER.name());
            startActivity(intent);
        }
    }
    private void startUp1() {
        HaveCustomer =0;
        ImageView employee_insert_item_image = findViewById(R.id.startup);
        int idimage,index =0;

        ArrayList<String> names =new ArrayList<>();
        names.add("Cappuccino");
        names.add("Cafe Latte");
        names.add("Cafe Mocha");
        names.add("Cappuccino Viennese");
        names.add("Espresso");
        names.add("Espresso Con Panna");
        names.add("Latte Macchiato");
        names.add("Cafe Americano");

        for(String name:names) {
            String tem = name;
            tem = tem.replaceAll("\\s+", "");
            tem = tem.toLowerCase();

            idimage = this.getResources().getIdentifier("hcmute.edu.vn.mssv18110278:drawable/" + tem, null, null);
            employee_insert_item_image.setImageResource(idimage);
            if(index==0)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 20000, "Xuất xứ từ Ý, một Cappuccino chuẩn sẽ gồm 3 phần đều nhau, đó là: cafe Espresso được pha với một lượng nước gấp đôi (Espresso Lungo), sữa nóng và sữa sủi bọt. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==1)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 25000, "Cafe Latte hiểu đơn giản là cà phê và sữa. Với những người uống không sành thì rất dễ nhầm lẫn Cafe Latte với Cappuchino vì 2 loại này đều cùng chung 3 thành phần chính. Tuy nhiên, điểm khác nhau ở chỗ, khi pha Cafe Latte, người ta chỉ cho lượng bọt sữa bằng một nửa lượng sữa nóng. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==2)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 30000, "Cafe Mocha là hỗn hợp giữa cafe Espresso được pha bằng hơi nước và chocolate nóng. Đặc trưng của loại thức uống này là vị thơm béo của kem tươi và vị ngậy của chocolate nóng.", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if (index==3)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 40000, "Cách làm tương tự Espresso nhưng được phục vụ kèm với kem sữa tươi ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==4)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 45000, "Là loại cafe rất được ưa chuộng tại Ý và Tây Ban Nha; được pha bằng cách cho nước bị ép dưới áp suất cao chảy qua một lượng cafe được xay cực mịn. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==5)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 15000, "Latte Macchiato (Latte) là một loại cafe nóng gồm cafe Espresso và sữa. Về cơ bản, Latte giống như cafe sữa nhưng ngọt hơn. Một Latte Macchiato được pha đúng chuẩn phải gồm 3 tầng rõ rệt, được rót vào theo thứ tự quy định và không trộn lẫn vào nhau ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==6)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 20000, "Thực tế, Americano chính là Espresso nhưng được pha loãng với lượng nước gấp đôi. Một Espresso được pha trực tiếp với nước nóng đựng sẵn trong tách Cappuchino là có thể phục vụ ngay cho khách hàng. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==7)
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 27000, "Xuất xứ từ Ý, một Cappuccino chuẩn sẽ gồm 3 phần đều nhau, đó là: cafe Espresso được pha với một lượng nước gấp đôi (Espresso Lungo), sữa nóng và sữa sủi bọt. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else
            DatabaseInsertHelper.insertProduct("Cafe Ý", name, 35000, "Xuất xứ từ Ý, một Cappuccino chuẩn sẽ gồm 3 phần đều nhau, đó là: cafe Espresso được pha với một lượng nước gấp đôi (Espresso Lungo), sữa nóng và sữa sủi bọt. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            index++;
        }
        index=0;
        names.clear();
        names.add("Robusta");
        names.add("Arabica");
        names.add("Cafe Cherry");
        names.add("Cafe Culi");
        names.add("Cafe Moka");

        for(String name:names) {
            String tem = name;
            tem = tem.replaceAll("\\s+", "");
            tem = tem.toLowerCase();

            idimage = this.getResources().getIdentifier("hcmute.edu.vn.mssv18110278:drawable/" + tem, null, null);
            employee_insert_item_image.setImageResource(idimage);
            if(index==0)
                DatabaseInsertHelper.insertProduct("Cafe Viet", name, 20000, "Hạt của cafe Robusta khá nhỏ, nhỏ hơn cả Arabica. Cà phê Robusta được sấy trực tiếp chứ không phải lên men, chính vì vậy loại cà phê này có vị khá đắng, uống đậm đà, rất phù hợp với cánh đàn ông. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==1)
                DatabaseInsertHelper.insertProduct("Cafe Viet", name, 25000, "Đây là một loại cà phê của Việt Nam có hạt hơi dài, được trồng ở độ cao trên 600m. Ở nước ta arabica thường chỉ được trồng tại Lâm Đồng. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==2)
                DatabaseInsertHelper.insertProduct("Cafe Viet", name, 30000, "Cafe Cherry được trồng ở những vùng đất khô đầy gió và nắng của vùng Cao Nguyên nước ta. Hạt cà phê vàng, sáng bóng rất đẹp. Cà phê Cherry dậy lên mùi hương thoang thoảng.", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if (index==3)
                DatabaseInsertHelper.insertProduct("Cafe Viet", name, 35000, "Một loại cà phê khác của nước ta có hạt tròn to bóng mẩy, đặc biệt so với các loại cafe khác là nó chỉ có một hạt duy nhất trong một trái. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else if(index==4)
                DatabaseInsertHelper.insertProduct("Cafe Viet", name, 40000, "Cà phê Moka là một trong các loại cafe thuộc chi Arabica, trồng tại Đà Lạt, Lâm Đồng. Trong các họ cà phê thì loại này là giống khó trồng nhất. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            else
                DatabaseInsertHelper.insertProduct("Cafe Viet", name, 30000, "Xuất xứ từ Ý, một Cappuccino chuẩn sẽ gồm 3 phần đều nhau, đó là: cafe Espresso được pha với một lượng nước gấp đôi (Espresso Lungo), sữa nóng và sữa sủi bọt. ", 1, ConverttoArrayByte(employee_insert_item_image), getBaseContext());
            index++;
        }




    }
    public byte[] ConverttoArrayByte(ImageView img)

    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}