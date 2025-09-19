import React, { useEffect, useState } from "react";

function Location() {
  const [response, setResponse] = useState(null);

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        async (position) => {
          const coord = {
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
          };

          try {
            const res = await fetch("http://localhost:8080/placesInfo", {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(coord), 
            });

            const data = await res.json();
            setResponse(data);
          } catch (err) {
            console.error("Error sending location to API", err);
          }
        },
        (error) => console.error("Unable to fetch location", error)
      );
    } else {
      console.error("Geolocation not supported by this browser");
    }
  }, []);
}

  export default Location;