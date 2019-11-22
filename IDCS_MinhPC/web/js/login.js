/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global firebase */

// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyCpeUdwecMQ6MFkSZND1rVbJvahH38VxXM",
    authDomain: "idcs-g21.firebaseapp.com",
    databaseURL: "https://idcs-g21.firebaseio.com",
    projectId: "idcs-g21",
    storageBucket: "idcs-g21.appspot.com",
    messagingSenderId: "705905343457",
    appId: "1:705905343457:web:f2e69ce0cf05b3b276f0a0",
    measurementId: "G-R34EK4R7Y4"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

//Get element
var txtusername = document.getElementById("txtusername");
var txtpassword = document.getElementById("txtpassword");
var btnlogin = document.getElementById("btnlogin");

btnlogin.addEventListener("click", e => {

    const user = txtusername.value;
    const pass = txtpassword.value;
    const auth = firebase.auth();
    const authen = auth.signInWithEmailAndPassword(user, pass);
    authen.catch(e => console.log(e.message));
    if (pass == "abcd8796") {
        alert("admin");
    }
});
