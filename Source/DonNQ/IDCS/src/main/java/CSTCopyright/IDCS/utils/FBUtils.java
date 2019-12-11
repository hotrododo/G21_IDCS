///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package CSTCopyright.IDCS.utils;
//
//import CSTCopyright.IDCS.controller.UserAccount;
////import com.google.api.core.ApiFuture;
////import com.google.auth.oauth2.GoogleCredentials;
////import com.google.cloud.firestore.DocumentSnapshot;
////import com.google.cloud.firestore.Firestore;
////import com.google.firebase.FirebaseApp;
////import com.google.firebase.FirebaseOptions;
////import com.google.firebase.cloud.FirestoreClient;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.concurrent.ExecutionException;
//
///**
// *
// * @author mac
// */
//public class FBUtils {
//
//
//    public static Firestore initDB() throws FileNotFoundException, IOException {
//
//        InputStream serviceAccount;
//
//        serviceAccount = new FileInputStream("/Users/mac/Downloads/idcs.json");
////        URL url = new URL("https://firebasestorage.googleapis.com/v0/b/idcs-62ee4.appspot.com/o/idcs-62ee4-firebase-adminsdk-8y5lo-b7a7dbdf7f.json?alt=media&token=361817d1-6cf2-40f5-a6a7-e08c71f2ed5a");
////        serviceAccount = url.openStream();
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://idcs-62ee4.firebaseio.com")
//                .build();
//
//        FirebaseApp.initializeApp(options);
//
//        Firestore db = FirestoreClient.getFirestore();
//        
//        System.out.println(db.collection("Users").document("8WFhZhzVUCzWuqazWSch"));
//
//        return db;
//    }
//
//    public static boolean addAccount(UserAccount user) throws IOException, InterruptedException, ExecutionException {
//        Firestore db = initDB();
////        DocumentReference docRef = db.collection("Users").document("alovelace");
////        // Add document data  with id "alovelace" using a hashmap
////        Map<String, Object> data = new HashMap<>();
////        data.put("email", user.getEmailAddress());
////        data.put("expDate", user.getExpiryDate());
////        data.put("fullName", user.getFullName());
////        data.put("password", user.getPassword());
////        data.put("gender", user.isGender());
////        data.put("phone", user.getPhoneNumber());
////        data.put("useCount", user.getUseCount());
////        data.put("userRule", user.getUserType());
////        data.put("userName", user.getUserName());
////        //asynchronously write data
////        ApiFuture<WriteResult> result = docRef.set(data);
////        // ...
////        // result.get() blocks on response
////        System.out.println("Update time : " + result.get().getUpdateTime());
//        ApiFuture<DocumentSnapshot> doc = db.collection("Users").document("8WFhZhzVUCzWuqazWSch").get();
//        DocumentSnapshot document = doc.get();
//        UserAccount users = null;
//        if (document.exists()) {
//            // convert document to POJO
//            users = document.toObject(UserAccount.class);
//            System.out.println(users);
//           
//        } else {
//            System.out.println("No such document!");
//        }
//
//// ...
//// query.get() blocks on response
////        DocumentSnapshot querySnapshot = query.get();
////        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
////        for (QueryDocumentSnapshot document : documents) {
//        System.out.println("User: " + document.getId());
//        System.out.println("password: " + document.getString("password"));
//
////        }
//        return true;
//    }
//}
