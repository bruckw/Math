/**
 * Created by Lily on 11/15/2015.
 */
public class PowerObject {
    private double approxEigenvalue;
    private Vector approxEigenvector;
    private int iterations = 0;
    private boolean converges = true;

    /**
     * Constructor for a power method object.
     */
    public PowerObject(double eigenvalue, Vector eigenvector) {
        this.approxEigenvalue = eigenvalue;
        this.approxEigenvector = eigenvector;
    }

    public boolean converges() {
        return converges;
    }

    public double getEigenvalue() {
        if (converges) {
            return approxEigenvalue;
        } else {
            return 0;
        }
    }

    public Vector getEigenvector() {
        if (converges) {
            return approxEigenvector;
        } else {
            return null;
        }
    }

    public int getIterations() {
        return iterations;
    }

    public void setConverges(boolean converges) {
        this.converges = converges;
    }

    public void setEigenvalue(double value) {
        this.approxEigenvalue = value;
    }

    public void setEigenvector(Vector vector) {
        this.approxEigenvector = vector;
    }

    public void incrementIterations() {
        this.iterations++;
    }

    @Override
    public String toString() {
        if (converges) {
            // At most keep 20 characters worth of eigenvalue
            String originalEigenvalueToString = "" + approxEigenvalue;
            String desiredPortionEigenvalueToString = "";
            for (int i = 0; i < originalEigenvalueToString.length() && i <= 20; i++) {
                desiredPortionEigenvalueToString += originalEigenvalueToString.substring(i, i+1);
            }

            String returnString = "";
            returnString += "Eigenvalue:  " + desiredPortionEigenvalueToString + "\n";
            returnString += "Eigenvector: " + approxEigenvector.toString() + "\n";
            returnString += "Iterations:  " + iterations;
            return returnString;
        }
        return "This matrix does not converge.";
    }
}
