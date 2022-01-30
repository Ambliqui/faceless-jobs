//document.querySelector(".btn1").addEventListener('click', e =>{
//	
//	e.preventDefault();
//	
//	Swal.fire({
//		title: "AVISO" ,
//		text: "Una vez publicada la oferta no se podrán modificar los datos y/o las habilidades.",
//		icon: "warning",
//		//html: "",
//		confirmButtonText: "Publicar",
//		footer: "<span style='color:red;'><b>¡INFORMACIÓN IMPORTANTE!</b></span>",
//		showCancelButton:true,
//		cancelButtonText: 'No publicar'
//	});
//});

function activarOferta() {

    Swal.fire({
        title: "AVISO" ,
		text: "Una vez publicada la oferta no se podrán modificar los datos y/o las habilidades.",
		icon: "warning",
		//html: "",
		confirmButtonText: "Publicar",
		footer: "<span style='color:red;'><b>¡INFORMACIÓN IMPORTANTE!</b></span>",
		showCancelButton:true,
		cancelButtonText: 'No publicar'
    }).then((result) => {
        if (result.isConfirmed) {
        document.getElementById("activarForm").submit();
        }
    })
}


//$('#activarOferta').on('submit',function(e){
//    e.preventDefault();
//    var form = $(this).parents('form');
//    swal({
//        title: "AVISO" ,
//		text: "Una vez publicada la oferta no se podrán modificar los datos y/o las habilidades.",
//		icon: "warning",
//		html: '<p>Una vez publicada la oferta no se podrán modificar los datos y/o las habilidades.</p>,
//		footer: "<span style='color:red;'><b>¡INFORMACIÓN IMPORTANTE!</b></span>",
//		showCancelButton:true,
//		cancelButtonText: 'No publicar'
//    }, function(isConfirm){
//        if (isConfirm) form.submit();
//    });
//});

//$('#activarForm').submit(function (e, params) {
//        var localParams = params || {};
//
//        if (!localParams.send) {
//            e.preventDefault();
//        }
//
//           //additional input validations can be done hear
//
//    swal({
//                title: "AVISO" ,
//				text: "Una vez publicada la oferta no se podrán modificar los datos y/o las habilidades.",
//				icon: "warning",
//				//html: "",
//				confirmButtonText: "Publicar",
//				footer: "<span style='color:red;'><b>¡INFORMACIÓN IMPORTANTE!</b></span>",
//				showCancelButton:true,
//				cancelButtonText: 'No publicar'
//            }, function (isConfirm) {
//                if (isConfirm) {
//                    $(e.currentTarget).trigger(e.type, { 'send': true });
//                } else {
//
//              //additional run on cancel  functions can be done hear
//
//            }
//        });
//});

//document.querySelector('borrar').addEventListener('click', xxx);
//
//document.querySelector(".btn1").addEventListener('click', function(){
//  Swal.fire("Our First Alert");
//});