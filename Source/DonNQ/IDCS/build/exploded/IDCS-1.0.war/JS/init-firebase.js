/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global firebase */

// TODO: Replace the following with your app's Firebase project configuration
var firebaseConfig = {
  apiKey: "AIzaSyDaBiEv-rS5vIhx56k6bIrlclRGCqUVD5A",
    authDomain: "idcs-62ee4.firebaseapp.com",
    databaseURL: "https://idcs-62ee4.firebaseio.com",
    projectId: "idcs-62ee4",
    storageBucket: "idcs-62ee4.appspot.com",
    messagingSenderId: "128593348135",
    appId: "1:128593348135:web:0a98e4a2c1429342f5b002",
    measurementId: "G-K5L61CH3NQ"
};

// Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.analytics();