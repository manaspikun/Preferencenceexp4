package com.techpalle.preferencenceexp4;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragOne extends Fragment {
    EditText et1,et2,et3;
    Button b1;
    TextView tv;
    Spinner spinner;
    String details[]={"name","salary","email"};
    ArrayAdapter<String>aa;


    public FragOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_frag_one, container, false);
        et1= (EditText) v.findViewById(R.id.edittext1);
        et2= (EditText) v.findViewById(R.id.edittext2);
        et3= (EditText) v.findViewById(R.id.edittext3);
        spinner= (Spinner) v.findViewById(R.id.spinner);
        b1= (Button) v.findViewById(R.id.button1);
        tv= (TextView) v.findViewById(R.id.textview1);
        aa=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,details);
        spinner.setAdapter(aa);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp= getActivity().getSharedPreferences("credentials",0);
                SharedPreferences.Editor editor=sp.edit();//open editor
                editor.putString("name",et1.getText().toString());//writename
                editor.putString("sal",et2.getText().toString());//writepw
                editor.putString("email",et3.getText().toString());
                editor.commit();//save in to preference file
                Toast.makeText(getActivity(), "store data", Toast.LENGTH_SHORT).show();


                SharedPreferences shar= getActivity().getSharedPreferences("credentials",0);
                String value1=sp.getString("name",null);
                String value2=sp.getString("sal",null);
                String value3=shar.getString("email",null);
                tv.setText(" Name : "+ value1+"\n Subject : "+value2+"\n Email : "+value3);

                return ;


            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getActivity(), "click:"+i, Toast.LENGTH_SHORT).show();
               // int j=spinner.getSelectedItemPosition();
                int j=i;
                if (j==0){
                    et1.setText("");
                    et1.requestFocus();
                    SharedPreferences sp=getActivity().getSharedPreferences("credentials",0);
                    SharedPreferences.Editor et=sp.edit();
                    et.putString("name",et1.getText().toString());


                }else if (j==1){
                    et2.setText("");
                    et2.requestFocus();
                    SharedPreferences sp=getActivity().getSharedPreferences("credentials",0);
                    SharedPreferences.Editor et=sp.edit();
                    et.putString("sal",et2.getText().toString());

                }else if (j==2){
                    et3.setText("");
                    et3.requestFocus();
                    SharedPreferences sp=getActivity().getSharedPreferences("credentials",0);
                    SharedPreferences.Editor et=sp.edit();
                    et.putString("email",et3.getText().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

}
