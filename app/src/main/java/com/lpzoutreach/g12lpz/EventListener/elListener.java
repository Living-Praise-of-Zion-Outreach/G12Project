package com.lpzoutreach.g12lpz.EventListener;

public class elListener {
        private String type;
        private elHandler handler;

        public elListener(String type, elHandler handler){
            this.type=type;
            this.handler=handler;
        }

        public String getType(){return this.type;}

        public elHandler getHandler() {return handler;}

}
