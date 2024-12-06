import axios from "axios";

export const shortURL = async(inputUrl,setShortenedUrl,setError) => {
    try {
        const response = await axios.post("http://localhost:8080/shorten/", {
            linkUrl:inputUrl
        });
        setShortenedUrl(response.data);
        console.log(response.data);
    } catch (error) {
        setError("Error shortening the URL. Please try again.");
        console.error(error);
    }
}

export const redirectUrl = async(linkId,setRedirectUrl,setError) => {
    try {
        const response = await axios.get(`http://localhost:8080/shorten/redirect/${linkId}`);
        console.log(`Redirect to URL: `,response.data)
    } catch (error) {
        setError("Error fetching the redirect URL. Please try again.");
        console.error(error)
    }
}
