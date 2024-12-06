import { useState } from 'react';
import './App.css';
import { shortURL } from './service/urlService';

function App() {
  const [inputUrl, setInputUrl] = useState("");
  const [shortenedUrl, setShortenedUrl] = useState("");
  const [error, setError] = useState("");
  const [origUrl,setOrigUrl] = useState("");

  const handleShortenUrl = async () => {
    setError("");
    setShortenedUrl(null);
    await shortURL(inputUrl,setShortenedUrl,setError);
  };
  
  return (
    <>
      <div className='result'>
          <p>QR CODE</p>
        <div className='qr-result'>
        {shortenedUrl && shortenedUrl.qrcode && (
          <img
            src={`data:image/png;base64,${shortenedUrl.qrcode}`}
            alt="QR Code"
            style={{ width: "250px", height: "250px" }}
          />
        )}
        </div>
        <div className='url-result'>
          {shortenedUrl && shortenedUrl.url && (
            <p>
              Short URL: <a href={inputUrl}>{shortenedUrl.url}</a>
            </p>
          )}
        </div>

        {error && <p style={{ color: "red" }}>{error} </p>}

      </div>
      <div className='default'>
          <p>Enter Link here</p>
          <input type="text"
            value={inputUrl}
            onChange= {(e) => setInputUrl(e.target.value)}
            placeholder="Enter URL to shorten"
          />
          <div className='button-box'>
            <button onClick={handleShortenUrl}>Shorten URL</button>
          </div>
      </div>
    </>
  )
}

export default App
