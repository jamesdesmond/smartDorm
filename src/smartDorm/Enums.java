package smartDorm;

/**
 * Created by james on 10/13/16.
 */
public class Enums {
    public enum People {
        //TODO: implement commented out section
        JAMES,//("foo@bar.com"),
        CARTER,//("stuff@things.net");

        /*People(String email) {
            this.email = email;
        }

        final public String email;*/
    }
    public enum WeatherInfo {
        TODAY (0),
        HIGH_TEMP (1),
        LOW_TEMP (18);
        private int index;
        WeatherInfo(int index) {
            this.index = index;
        }
        public int getIndex() {
            return index;
        }
    }
}
