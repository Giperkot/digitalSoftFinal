const orgsPageScript = function(){
    $( "button#addOrg" ).click(function( e ) {
        console.log(e.target.id)
        $("form#organizationForm").show();
        $("div#actionBlockOrg").hide();
    });
};
