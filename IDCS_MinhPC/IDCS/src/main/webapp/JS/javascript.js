/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function frmValidate() {
    var val = document.frmDomain.name.value;
    if (/^([a-z0-9]([-a-z0-9]*[a-z0-9])?\\.)+((a[cdefgilmnoqrstuwxz]|aero|arpa)|(b[abdefghijmnorstvwyz]|biz)|(c[acdfghiklmnorsuvxyz]|cat|com|coop)|d[ejkmoz]|(e[ceghrstu]|edu)|f[ijkmor]|(g[abdefghilmnpqrstuwy]|gov)|h[kmnrtu]|(i[delmnoqrst]|info|int)|(j[emop]|jobs)|k[eghimnprwyz]|l[abcikrstuvy]|(m[acdghklmnopqrstuvwxyz]|mil|mobi|museum)|(n[acefgilopruz]|name|net)|(om|org)|(p[aefghklmnrstwy]|pro)|qa|r[eouw]|s[abcdeghijklmnortvyz]|(t[cdfghjklmnoprtvwz]|travel)|u[agkmsyz]|v[aceginu]|w[fs]|y[etu]|z[amw])$/i.test(val)) {
        alert("Valid Domain Name");
        return true;
    } else {
        alert("Enter Valid Domain Name");
        val.name.focus();
        return false;
    }
}
function blackColor(uds, mess) {
    $(document).ready(function () {
        if (uds < 5) {
            $("#navShow")[0].children[uds].setAttribute("style", "background-color: rgb(25,27,29); border-left: solid 2pt orange;");
        }
        if (uds === 5) {
            $("#dirShow")[0].children[0].setAttribute("style", "font-size: 10pt");
            $("#dirShow")[0].children[1].setAttribute("style", "font-size: 1.5rem");
            $("#navShow")[0].children[5].setAttribute("style", "background-color: rgb(25,27,29); border-left: solid 2pt orange;");
            $('.needs-validation').prop("visibility", "hidden");
        }
        if (mess === null || mess === "") {
            $('.abel-message').prop("style", "visibility: hidden");
        } else {
            $('.abel-message').prop("style", "visibility: visible");
            $('.abel-message').text(mess);
        }
        for (var i = 1; i < 6; i++) {
            if (i !== uds) {
                $(".dis-" + i).prop("style", "visibility: hidden; z-index: -1");
            }
        }
        $(".dis-" + uds).prop("style", "visibility: visible; z-index: 1");
    });
    $(document).ready(function () {
        $("#btn-disabled-fiels").click(function () {
            if ($('#txt1').prop("disabled") === true) {
                $('.user-info-tab-row input').prop("disabled", false);
                $('.user-info-tab-row input').prop("style", "border-bottom: solid 1px darkred;");
                $('.btn-update button').prop("style", "visibility: visible");
            } else
            {
                $('.user-info-tab-row input').prop("disabled", true);
                $('.user-info-tab-row input').prop("style", "border-bottom: none;");
                $('.btn-update button').prop("style", "visibility: hidden");
            }
        });
        $('.dirShow p').click(function () {
            $("#dirShow")[0].children[0].setAttribute("style", "font-size: 10pt; padding-top: 15pt");
            $("#dirShow")[0].children[1].setAttribute("style", "font-size: 1.5rem");
            $('.needs-validation').prop("visibility", "hidden");
            uds = 5;
            for (var i = 1; i < 6; i++) {
                if (i !== uds) {
                    $(".dis-" + i).prop("style", "visibility: hidden; z-index: -1");
                }
            }
            $(".dis-" + uds).prop("style", "visibility: visible; z-index: 1");
        });
        $('.dirShow h4').click(function () {
            $("#dirShow")[0].children[1].setAttribute("style", "font-size: 10pt; padding-top: 0");
            $("#dirShow")[0].children[0].setAttribute("style", "font-size: 1.5rem");
            $('.needs-validation').prop("visibility", "hidden");
            uds = 1;
            for (var i = 1; i < 6; i++) {
                if (i !== uds) {
                    $(".dis-" + i).prop("style", "visibility: hidden; z-index: -1");
                }
            }
            $(".dis-" + uds).prop("style", "visibility: visible; z-index: 1");

        });

    });
}
function payment() {
    uds = 2;
    for (var i = 1; i < 6; i++) {
        if (i !== uds) {
            $(".dis-" + i).prop("style", "visibility: hidden; z-index: -1");
            if (i < 5) {
                $("#navShow")[0].children[i].setAttribute("style", "background: none; border-left: none;");
            }
        }
    }
    $(".dis-" + uds).prop("style", "visibility: visible; z-index: 1");
    $('.user-profiles img').prop("src", "../IDCS/IMG/pay.jpg");
    $('.user-profiles p').text("Scan the QR code to pay faster with VNPAY-QR\nPay $49.99");
    $('#dirShow').prop("style", "visibility: hidden; z-index: -1");
    $("#navShow")[0].children[uds].setAttribute("style", "background-color: rgb(25,27,29); border-left: solid 2pt orange;");
    $('#btn-disabled-fiels').prop("style", "visibility: hidden; z-index: -1");
}
function userinfo() {
    uds = 1;
    for (var i = 1; i < 6; i++) {
        if (i !== uds) {
            $(".dis-" + i).prop("style", "visibility: hidden; z-index: -1");
            if (i < 5) {
                $("#navShow")[0].children[i].setAttribute("style", "background: none; border-left: none;");
            }
        }
    }
    $(".dis-" + uds).prop("style", "visibility: visible; z-index: 1");
    $('.user-profiles img').prop("src", "../IDCS/IMG/premium-avatar.png");
    $('.user-profiles p').text($('.left-menu-user-full-names p').text());
    $('#dirShow').prop("style", "visibility: visible; z-index: 1");
    $("#navShow")[0].children[uds].setAttribute("style", "background-color: rgb(25,27,29); border-left: solid 2pt orange;");
    $('#btn-disabled-fiels').prop("style", "visibility: visible; z-index: 1");
}
function contact() {
    uds = 3;
    for (var i = 1; i < 6; i++) {
        if (i !== uds) {
            $(".dis-" + i).prop("style", "visibility: hidden; z-index: -1");
            if (i < 5) {
                $("#navShow")[0].children[i].setAttribute("style", "background: none; border-left: none;");
            }
        }
    }
    $(".dis-" + uds).prop("style", "visibility: visible; z-index: 1");
    $('.user-profiles img').prop("src", "../IDCS/IMG/logo_idcs.png");
    $('.user-profiles p').text("Internet-connected Devices Checking System");
    $('#dirShow').prop("style", "visibility: hidden; z-index: -1");
    $("#navShow")[0].children[uds].setAttribute("style", "background-color: rgb(25,27,29); border-left: solid 2pt orange;");
    $('#btn-disabled-fiels').prop("style", "visibility: hidden; z-index: -1");
}
function help() {
    uds = 4;
    for (var i = 1; i < 6; i++) {
        if (i !== uds) {
            $(".dis-" + i).prop("style", "visibility: hidden; z-index: -1");
            if (i < 5) {
                $("#navShow")[0].children[i].setAttribute("style", "background: none; border-left: none;");
            }
        }
    }
    $(".dis-" + uds).prop("style", "visibility: visible; z-index: 1");
    $('.user-profiles img').prop("src", "../IDCS/IMG/logo_idcs.png");
    $('.user-profiles p').text("Internet-connected Devices Checking System");
    $('#dirShow').prop("style", "visibility: hidden; z-index: -1");
    $("#navShow")[0].children[uds].setAttribute("style", "background-color: rgb(25,27,29); border-left: solid 2pt orange;");
    $('#btn-disabled-fiels').prop("style", "visibility: hidden; z-index: -1");
}

function hasErrorAlert(erroMess) {
    $(document).ready(function () {
        title = 'Alert';
        if (erroMess)
            $('<div></div>').html(erroMess).dialog({
                title: title,
                resizable: false,
                modal: true,
                buttons: {
                    'Ok': function () {
                        $(this).dialog('close');
                    },
                    'Cancel': function () {
                        $(this).dialog('close');
                    }
                }
            });
    });
}






function checkConfirmPass() {
    var p = document.getElementById("password").value;
    var confP = document.getElementById("repass").value;
    var notif = "Confirm password not match!";
    if (p != confP) {
        document.getElementById("notif").innerHTML = notif;
        return false;
    }
    return true;
}
function checkConfirmCreate() {
    $(".mdl-data-table tr").on('click', '#btn', function () {
        var row = $(this).closest('tr');
        var col2 = row.find('td:eq(2)').text();
        $("#username").val(col2);
    });
}
function confirmYN() {
    $(".mdl-data-table tr").on('click', '#btn', function () {
        var row = $(this).closest('tr');
        var col2 = row.find('td:eq(2)').text();
        $("#username").val(col2);

    });
}


function getInfoToForm() {
    $(".mdl-data-table tr").on('click', '#btn', function () {
        var row = $(this).closest('tr');
        var col1 = row.find('td:eq(1)').text();
        var col2 = row.find('td:eq(2)').text();
        var col3 = row.find('td:eq(3)').text();
        var col4 = row.find('td:eq(4)').text();
        var col5 = row.find('td:eq(5)').text();
        var col6 = row.find('td:eq(6)').text();
        var col7 = row.find('td:eq(7)').text();
        var col8 = row.find('td:eq(8)').text();
        $("#fullname").val(col1);
        $("#username").val(col2);
        $("#birthday").val(col2);
        $("#gender").val(col2);
        $("#phone").val(col2);
        $("#email").val(col2);
        $("#address").val(col2);
        $("#expiryDate").val(col2);
    });
}

function displayFormEdit() {
    $(".mdl-data-table tr").on('click', '#btn', function () {
        var row = $(this).closest('tr');
        var col1 = row.find('td:eq(1)').text();
        var col2 = row.find('td:eq(2)').text();
        var col3 = row.find('td:eq(3)').text();
        var col4 = row.find('td:eq(4)').text();
        var col5 = row.find('td:eq(5)').text();
        var col6 = row.find('td:eq(6)').text();
        var col7 = row.find('td:eq(7)').text();
        var col8 = row.find('td:eq(8)').text();
        $("#fullname").val(col1);
        $("#username").val(col2);
        $("#birthday").val(col7);
        $("#usecount").val(col8);
        $("#phone").val(col6);
        $("#email").val(col4);
        $("#address").val(col5);
        $("#expiryDate").val(col7);
    })
    document.getElementById('popup').style.display = "flex";
    document.getElementById('popup').style.zIndex = "1";
    document.getElementById('userList').style.display = "none";
    document.getElementById('userList').style.filter = "blur(1px)";
    document.getElementById('userList').style.pointerEvents = "none";
    return false;
}
function hideDialog() {
    document.getElementById('popup').style.display = "none";
    document.getElementById('popup').style.zIndex = "-1";
    document.getElementById('userList').style.display = "block";
    document.getElementById('userList').style.filter = "none";
    document.getElementById('userList').style.pointerEvents = "auto";
}
function ConfirmDialog(message) {
    $('<div></div>').appendTo('body')
            .html('<div><h6>' + message + '?</h6></div>')
            .dialog({
                modal: true,
                title: 'Delete account',
                zIndex: 100,
                autoOpen: true,
                width: 'auto',
                resizable: false,
                buttons: {
                    Yes: function () {
                        // $(obj).removeAttr('onclick');                                
                        // $(obj).parents('.Parent').remove();
                        confirmYN();
                        $(this).dialog("close");
                        return true;
                    },
                    No: function (event, ui) {
                        $(this).remove();
                    }
                },
                close: function (event, ui) {
                    $(this).remove();
                }
            });
}
;


