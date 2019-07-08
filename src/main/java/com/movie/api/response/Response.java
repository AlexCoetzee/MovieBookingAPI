package com.movie.api.response;

import org.springframework.http.HttpStatus;

public class Response {
    private HttpStatus responseStatus;
    private String message;
    private Object responseBody;

    private Response() {}

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public static class Builder {
        private HttpStatus responseStatus;
        private String message;
        private Object responseBody;

        public Builder(Object responseBody) {
            this.responseBody = responseBody;
        }
        public Builder responseStatus(HttpStatus status){
            this.responseStatus = status;
            return this;
        }
        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Response build(){
            //Here we create the actual response object, which is always in a fully initialised state when it's returned.
            Response response = new Response();
            response.responseStatus = this.responseStatus;
            response.message = this.message;
            response.responseBody = this.responseBody;
            return response;
        }
    }
}
