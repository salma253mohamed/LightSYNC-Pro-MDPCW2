package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListColors extends AppCompatActivity {
//
//    @Override
//    ListView myListview;
//    List<String> SceneList;
//
//    DatabaseReference studentDbRef;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_colors);
//
//        myListview = findViewById(R.id.myListView);
//        SceneList = new ArrayList<>();
//
//        studentDbRef = FirebaseDatabase.getInstance().getReference("Students");
//
//        studentDbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                SceneList.clear();
//
//                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()){
//                    Students students = studentDatasnap.getValue(Students.class);
//                    studentsList.add(students);
//                }
//
//                ListAdapter adapter = new ListAdapter(RetreiveDataActivity.this,studentsList);
//                myListview.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        //set itemLong listener on listview item
//
//        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Students students = studentsList.get(position);
//                showUpdateDialog(students.getId(), students.getName());
//
//                return false;
//            }
//        });
//    }
//
//    private void showUpdateDialog(final String id, String name){
//
//        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View mDialogView = inflater.inflate(R.layout.update_dialog, null);
//
//        mDialog.setView(mDialogView);
//
//        //create views refernces
//        final EditText etUpdateName = mDialogView.findViewById(R.id.etUpdateName);
//        final EditText etUpdateRollno = mDialogView.findViewById(R.id.etUpdateRollno);
//        final Spinner mSpinner = mDialogView.findViewById(R.id.updateSpinner);
//        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
//        Button btnDelete = mDialogView.findViewById(R.id.btnDelete);
//
//        mDialog.setTitle("Updating " + name +" record");
//
//        final AlertDialog alertDialog = mDialog.create();
//        alertDialog.show();
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //here we will update data in database
//                //now get values from view
//
//                String newName = etUpdateName.getText().toString();
//                String newRollno = etUpdateRollno.getText().toString();
//                String newCourse = mSpinner.getSelectedItem().toString();
//
//                updateData(id,newName,newRollno,newCourse);
//
//                Toast.makeText(RetreiveDataActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
//                alertDialog.dismiss();
//            }
//
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteRecord(id);
//
//                alertDialog.dismiss();
//            }
//        });
//    }
//
//    private void showToast(String message){
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    private void deleteRecord(String id){
//        //create reference to database
//        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Students").child(id);
//        //we referencing child here because we will be delete one record not whole data data in database
//        //we will use generic Task here so lets do it..
//
//        Task<Void> mTask = DbRef.removeValue();
//        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                showToast("Deleted");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                showToast("Error deleting record");
//            }
//        });
//    }
//
//    private void updateData(String id, String name, String rollno, String course){
//
//        //creating database reference
//        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Students").child(id);
//        Students students = new Students(id, name, rollno, course);
//        DbRef.setValue(students);
//    }
}