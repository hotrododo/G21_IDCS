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




