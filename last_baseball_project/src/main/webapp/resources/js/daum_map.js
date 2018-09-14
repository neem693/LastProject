/**
 * 
 */

var map;
var mapContrainer;
var infowindow;
var ps;
var geocoder;
var marker;



var real_addr;//실제주소를 여기에다가 삽입할 것입니다.
var coor_x; //x좌표
var coor_y; //y좌표

// 지도를 생성합니다
function make_map() {
	real_addr = document.getElementById("real_addr");
	
	coor_x = document.getElementById("coor_x");
	coor_y = document.getElementById("coor_y");
	
	
	
	infowindow = new daum.maps.InfoWindow({
		zIndex : 1
	});

	mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center : new daum.maps.LatLng(35.84166723636994, 128.68183054128727), // 지도의 중심좌표
									//  y      ,    x
		level : 3
	// 지도의 확대 레벨
	};

	map = new daum.maps.Map(mapContainer, mapOption);
	// 장소 검색 객체를 생성합니다

	ps = new daum.maps.services.Places();

	geocoder = new daum.maps.services.Geocoder();

	marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
	infowindow = new daum.maps.InfoWindow({
		zindex : 1
	}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
	
//	marker.setPosition(new daum.maps.LatLng(35.84166723636994, 128.68183054128727));
//	marker.setMap(map);
//	수정할때 이걸로 사용할 것

	// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
	searchAddrFromCoords(map.getCenter(), displayCenterInfo);

	// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
	daum.maps.event.addListener(map, 'click', function(mouseEvent) {
		searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
			if (status === daum.maps.services.Status.OK) {
//				var detailAddr = !!result[0].road_address ? '<div>도로명주소 : '
//						+ result[0].road_address.address_name + '</div>' : '';
				
//				detailAddr += '<div>지번 주소 : ' + result[0].address.address_name
//						+ '</div>';
//
//				var content = '<div class="bAddr">'
//						+ '<span class="title">법정동 주소정보</span>' + detailAddr
//						+ '</div>';

				// 마커를 클릭한 위치에 표시합니다
				marker.setPosition(mouseEvent.latLng);
				marker.setMap(map);
				//////name 추가////
				real_addr.value = !!result[0].road_address ? result[0].road_address.address_name + "/": ''
				real_addr.value += result[0].address.address_name;
				coor_x.value = mouseEvent.latLng.ib;
				coor_y.value = mouseEvent.latLng.jb;
///////name 추가/////
				// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
				//infowindow.setContent(content);
				//infowindow.open(map, marker);
			}
		});
	});

	// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
	daum.maps.event.addListener(map, 'idle', function() {
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	});

	// 키워드로 장소를 검색합니다
	// 키워드 검색 완료 시 호출되는 콜백함수 입니다

}
function placesSearchCB(data, status, pagination) {
	if (status === daum.maps.services.Status.OK) {

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		// LatLngBounds 객체에 좌표를 추가합니다
		var bounds = new daum.maps.LatLngBounds();

		for (var i = 0; i < 1; i++) {
			// displayMarker(data[i]);
			bounds.extend(new daum.maps.LatLng(data[i].y, data[i].x));
		}

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		map.setBounds(bounds);
		map.setLevel(3);
	}
}

function searchAddrFromCoords(coords, callback) {
	// 좌표로 행정동 주소 정보를 요청합니다
	geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

function searchDetailAddrFromCoords(coords, callback) {
	// 좌표로 법정동 상세 주소 정보를 요청합니다
	geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
	if (status === daum.maps.services.Status.OK) {
		var infoDiv = document.getElementById('centerAddr');

		for (var i = 0; i < result.length; i++) {
			// 행정동의 region_type 값은 'H' 이므로
			if (result[i].region_type === 'H') {
				infoDiv.innerHTML = result[i].address_name;
				break;
			}
		}
	}
}