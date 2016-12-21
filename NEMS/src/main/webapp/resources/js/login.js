/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function changePassword(checked)
{
    //alert(changePswCheckBox.checked);
    if(checked){
       // PF("newPassword").show();
       $("table tr.row4").show();
       $("table tr.row5").show();
    }else{
        $("table tr.row4").hide();
        $("table tr.row5").hide();
     //   PF("newPassword").hide();
    }
}
