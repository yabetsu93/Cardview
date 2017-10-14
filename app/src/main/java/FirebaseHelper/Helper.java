package FirebaseHelper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import ViewModel.Nature;

/**
 * Created by jabespauya on 10/14/2017 AD.
 */

public class Helper {

    public FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();


    public void writeNewNatureDetail(Map<String, Object> map) {

        String NatureId = mDatabaseReference.child("112233444").child("NatureDetails").push().getKey();
        String imagePath = "http://medifoods.my/wp-content/uploads/2015/03/cover-menu-westernsoup1.jpg";
        String title = map.get("title").toString();
        String description = map.get("description").toString();

        Nature nature = new Nature(imagePath, title, description);

        mDatabaseReference.child("112233444").child("NatureDetails").child(NatureId).setValue(nature);
    }
}
