public class BrojacThread implements Runnable{

    private int vremeDoPorudzbineUSek;
    private String request;

    public BrojacThread(int vremeDoPorudzbine, String request){
        this.request = request;
        this.vremeDoPorudzbineUSek = vremeDoPorudzbine * 60;
    }

    @Override
    public void run() {
        while (vremeDoPorudzbineUSek > 0){
            try {
                vremeDoPorudzbineUSek--;
                Thread.sleep(25);

                if(vremeDoPorudzbineUSek % 60 == 0 || vremeDoPorudzbineUSek % 60 == 59)
                    Server.azurirajFrejm(request, String.valueOf(vremeDoPorudzbineUSek / 60));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
