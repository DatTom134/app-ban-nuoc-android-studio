package com.example.appbannuoc;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import Api.apiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Activity extends AppCompatActivity {

    Database database;
    ListView listView;
    AdapterSanPham adapterSanPham;
    ArrayList<SanPhamTable> sanphams;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_sanpham);

        listView = (ListView) findViewById(R.id.lvSanPham);
        sanphams = new ArrayList<SanPhamTable>();
        adapterSanPham = new AdapterSanPham(this, R.layout.layout_viewsanphams, sanphams);
        listView.setAdapter(adapterSanPham);
//        LoadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnThemSanPham)
        {
            DialogThem();
        }
        else if (item.getItemId() == R.id.btnLoadSanPham)
        {
            LoadSanPham();
        }
        else if (item.getItemId() == R.id.btnQuit)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void LoadSanPham() {
        apiServices.apiservices.getData().enqueue(new Callback<List<SanPhamTable>>() {
            @Override
            public void onResponse(Call<List<SanPhamTable>> call, Response<List<SanPhamTable>> response) {
                if(response.isSuccessful())
                {
                    List<SanPhamTable> sanphamlist = response.body();
                    sanphams.clear();
                    for (int i = 0; i < sanphamlist.size(); i++)
                    {
                        if (sanphamlist.get(i).isTRANGTHAI_SP())
                        {
                            SanPhamTable sanPhamTable = sanphamlist.get(i);
                            sanphams.add(sanPhamTable);
                        }
                    }
                    adapterSanPham.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(Home_Activity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SanPhamTable>> call, Throwable t) {
                Toast.makeText(Home_Activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        Database database = new Database(this, "QuanLyQuanNuoc.sqlite", null, 1);
//        String query = "SELECT * FROM SANPHAM";
//        Cursor SanPhamRows = database.GetData(query);
//        sanphams.clear();
//        while (SanPhamRows.moveToNext())
//        {
//            SanPhamTable sanPhamTable = new SanPhamTable(SanPhamRows.getInt(0), SanPhamRows.getString(1),
//                    SanPhamRows.getInt(2), SanPhamRows.getInt(3), SanPhamRows.getInt(4),
//                    SanPhamRows.getInt(5), SanPhamRows.getInt(6), SanPhamRows.getInt(7));
//            sanphams.add(sanPhamTable);
//        }
//        adapterSanPham.notifyDataSetChanged();
    }

    public void DialogDelete(int id) {
        Dialog dialog_deleteSP = new Dialog(this);
        dialog_deleteSP.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_deleteSP.setContentView(R.layout.dialog_delete_sanpham);
        dialog_deleteSP.show();

        Button btnOK, btnCancel;
        btnOK = dialog_deleteSP.findViewById(R.id.btnOk_dialog);
        btnCancel = dialog_deleteSP.findViewById(R.id.btnCancel_dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_deleteSP.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiServices.apiservices.DeleteSanPham(id).enqueue(new Callback<List<SanPhamTable>>() {
                    @Override
                    public void onResponse(Call<List<SanPhamTable>> call, Response<List<SanPhamTable>> response) {
                        if (response.isSuccessful())
                        {
                            Toast.makeText(Home_Activity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Home_Activity.this, "xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SanPhamTable>> call, Throwable t) {
                        Toast.makeText(Home_Activity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog_deleteSP.dismiss();
                LoadSanPham();
            }
        });
    }

    public void DialogEdit(SanPhamTable sanpham) {
        Dialog dialog_edit = new Dialog(this);
        dialog_edit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_edit.setContentView(R.layout.dialog_edit_sanpham);
        dialog_edit.show();

        //hiện thị ID của sản phẩm được chỉnh sửa
        TextView tvTitle;
        tvTitle = dialog_edit.findViewById(R.id.tvTitle);
        tvTitle.setText("chỉnh sửa sản phẩm id: " + sanpham.getSP_ID());

        Button btnOK_dialog, btnCancel_dialog;
        btnCancel_dialog = dialog_edit.findViewById(R.id.btnCancel_dialog);
        btnOK_dialog = dialog_edit.findViewById(R.id.btnOk_dialog);
        int Id = (int) sanpham.getSP_ID();
        btnCancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit.dismiss();
            }
        });

        btnOK_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtTENSP,edtGIA, edtIDDANHMUC, edtSLTK;
                RadioGroup rdbtnSPBC, rdbtnTTSP;
                edtTENSP = dialog_edit.findViewById(R.id.edtTENSP_dialog);
                edtGIA = dialog_edit.findViewById(R.id.edtGIA_dialog);
                edtIDDANHMUC = dialog_edit.findViewById(R.id.edtIDDANHMUC_dialog);
                edtSLTK = dialog_edit.findViewById(R.id.edtSLTK_dialog);
                rdbtnSPBC = dialog_edit.findViewById(R.id.rdbtnSPBC_dialog);
                boolean valueOfSPBC = Boolean.valueOf(((RadioButton) dialog_edit.findViewById(rdbtnSPBC.getCheckedRadioButtonId())).getText().toString());
                rdbtnTTSP = dialog_edit.findViewById(R.id.rdbtnTTSP_dialog);
                boolean valueOfTTSP = Boolean.valueOf(((RadioButton) dialog_edit.findViewById(rdbtnTTSP.getCheckedRadioButtonId())).getText().toString());
                apiServices.apiservices.updateSanPham(Id,
                                                    edtTENSP.getText().toString(),
                                                    Integer.parseInt(edtGIA.getText().toString()),
                                                    Integer.parseInt(edtIDDANHMUC.getText().toString()),
                                                    valueOfSPBC,
                                                    Integer.parseInt(edtSLTK.getText().toString()),
                                                    valueOfTTSP).enqueue(new Callback<List<SanPhamTable>>() {
                    @Override
                    public void onResponse(Call<List<SanPhamTable>> call, Response<List<SanPhamTable>> response) {
                        if (response.isSuccessful())
                        {
                            Toast.makeText(Home_Activity.this, "Sửa Thành công", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Home_Activity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog_edit.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<SanPhamTable>> call, Throwable t) {
                        Toast.makeText(Home_Activity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }



    private void DialogThem() {
        Dialog dialog_them = new Dialog(this);
        dialog_them.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_them.setContentView(R.layout.dialog_them_sanpham);
        dialog_them.show();
        Button btnOK, btnCancel;
        btnOK = dialog_them.findViewById(R.id.btnOk_dialog);
        btnCancel = dialog_them.findViewById(R.id.btnCancel_dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_them.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtTENSP,edtGIA, edtIDDANHMUC, edtSLTK;
                RadioGroup rdbtnSPBC, rdbtnTTSP;
                edtTENSP = dialog_them.findViewById(R.id.edtTENSP_dialog);
                edtGIA = dialog_them.findViewById(R.id.edtGIA_dialog);
                edtIDDANHMUC = dialog_them.findViewById(R.id.edtIDDANHMUC_dialog);
                edtSLTK = dialog_them.findViewById(R.id.edtSLTK_dialog);
                rdbtnSPBC = dialog_them.findViewById(R.id.rdbtnSPBC_dialog);
                boolean valueOfSPBC = Boolean.valueOf(((RadioButton) dialog_them.findViewById(rdbtnSPBC.getCheckedRadioButtonId())).getText().toString());
                rdbtnTTSP = dialog_them.findViewById(R.id.rdbtnTTSP_dialog);
                boolean valueOfTTSP = Boolean.valueOf(((RadioButton) dialog_them.findViewById(rdbtnTTSP.getCheckedRadioButtonId())).getText().toString());
                apiServices.apiservices.addSanPham(edtTENSP.getText().toString(),
                                                Integer.parseInt(edtGIA.getText().toString()),
                                                Integer.parseInt(edtIDDANHMUC.getText().toString()),
                                                valueOfSPBC,
                                                Integer.parseInt(edtSLTK.getText().toString()),
                                                valueOfTTSP).enqueue(new Callback<List<SanPhamTable>>() {
                    @Override
                    public void onResponse(Call<List<SanPhamTable>> call, Response<List<SanPhamTable>> response) {
                        if (response.isSuccessful())
                        {
                            Toast.makeText(Home_Activity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                        dialog_them.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<SanPhamTable>> call, Throwable t) {
                        Toast.makeText(Home_Activity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
