package Domain;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TrainingSessionHandler {
    private Map<Integer, TrainingSession> book = this.readBooking();

    private static TrainingSessionHandler instance = null;

    public TrainingSessionHandler() {
    }

    private synchronized static void createInstance() {
        if (instance == null)
            instance = new TrainingSessionHandler();
    }

    public static TrainingSessionHandler getInstance() {
        if (instance == null)
            createInstance();
        return instance;
    }

    private void updatePackage(String sessionID, TrainingSession trainingSession) {
        this.book.put(Integer.parseInt(sessionID), trainingSession);

        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("booking.dat"));
            Throwable var19 = null;

            try {
                stream.writeObject(this.book);
            } catch (Throwable var15) {
                var19 = var15;
                throw var15;
            } finally {
                if (stream != null) {
                    if (var19 != null) {
                        try {
                            stream.close();
                        } catch (Throwable var14) {
                            var19.addSuppressed(var14);
                        }
                    } else {
                        stream.close();
                    }
                }

            }
        } catch (Exception var17) {
            var17.printStackTrace();
            System.exit(0);
        }

        System.out.println("booking added successfully");
    }

    public void deleteBooking(int packageID) {
        ObjectOutputStream stream = null;
        try {
            stream = new ObjectOutputStream(new FileOutputStream("booking.dat"));
            this.book.remove(packageID);
            stream.writeObject(this.book);
            stream.close();
        } catch (Exception e) {
            System.out.println("There was an error during the deletion process");
            e.printStackTrace();
        }
        System.out.println("trainingPackage deleted successfully");
    }

    public Map<Integer, TrainingSession> readBooking() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("booking.dat"));
            Throwable var38 = null;

            try {
                this.book = (Map) stream.readObject();
            } catch (Throwable var32) {
                var38 = var32;
                throw var32;
            } finally {
                if (stream != null) {
                    if (var38 != null) {
                        try {
                            stream.close();
                        } catch (Throwable var31) {
                            var38.addSuppressed(var31);
                        }
                    } else {
                        stream.close();
                    }
                }

            }
        } catch (FileNotFoundException var36) {
            try {
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("booking.dat"));
                Throwable var3 = null;

                try {
                    if (this.book == null) {
                        this.book = new HashMap();
                    }

                    stream.writeObject(this.book);
                } catch (Throwable var30) {
                    var3 = var30;
                    throw var30;
                } finally {
                    if (stream != null) {
                        if (var3 != null) {
                            try {
                                stream.close();
                            } catch (Throwable var29) {
                                var3.addSuppressed(var29);
                            }
                        } else {
                            stream.close();
                        }
                    }
                }
            } catch (Exception var34) {
                var34.printStackTrace();
                System.exit(0);
            }
        } catch (Exception var37) {
            var37.printStackTrace();
            System.exit(0);
        }

        return this.book;
    }

    public Map<Integer, TrainingSession> saveBooking(TrainingSession trainingSession) {
        try {
            ObjectOutputStream wstream = new ObjectOutputStream(new FileOutputStream("booking.dat"));
            Throwable var3 = null;

            if (this.book == null) {
                this.book = new HashMap();
            } else {
                this.book.put(Integer.parseInt(trainingSession.getSessionId()), trainingSession);
            }

            wstream.writeObject(this.book);
            wstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return this.book;

    }
}

