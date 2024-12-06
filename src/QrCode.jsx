import React from "react";

const QRCodeDisplay = ({ data }) => {
  const qrCodeImage = `data:image/png;base64,${data.qrcode}`;

  return (
    <div>
      {/* Display QR Code */}
      <img src={qrCodeImage} alt="QR Code" style={{ width: "200px", height: "200px" }} />
      
      {/* Provide Link */}
      <a href={data.url} target="_blank" rel="noopener noreferrer">
        Visit Link
      </a>
    </div>
  );
};

export default QRCodeDisplay;
