


function mostraLugar(address) {
	var map = new GMap2(document.getElementById("mps"));
	
	var geocoder = new GClientGeocoder();
	
	  geocoder.getLatLng(
	    address,
	    function(point) {
	      if (!point) {
	        alert(address + " not found");
	      } else {
	        map.setCenter(point, 13);
	        var marker = new GMarker(point);
	        map.addOverlay(marker);
	        marker.openInfoWindowHtml(address);
	      }
	    }
	  );
	}