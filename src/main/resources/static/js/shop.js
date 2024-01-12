function readURL(input, size) {
  let maxSize = size * 1024 * 1024; //* 5MB 사이즈 제한
  let fileSize = input.files[0].size; //업로드한 파일용량

  if(fileSize > maxSize){
  	alert("파일첨부 사이즈는 " + size + "MB 이내로 가능합니다.");
  	input.value = null; //업로드한 파일 제거
  	return;
  }

  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('preview').src = "";
  }
}

function resetImg(img){
	if(!img.src) return;
	if(confirm("이미지를 제거하시겠습니까?")){
		img.src = "";
		document.getElementById("profile").value = null;
		alert("제거 하였습니다.");
	}else{
	}
}

function formatNumber(input) {
  // 숫자 이외의 문자 및 콤마 제거
  const inputValue = input.value.replace(/[^0-9]/g, '');

  // 숫자에 콤마 추가
  input.value = numberWithCommas(inputValue);
}

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}