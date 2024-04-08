import java.awt.*;
import java.awt.print.*;

public class Reportprintable implements Printable {
    private Component componentToPrint;

    public Reportprintable(Component componentToPrint) {
        this.componentToPrint = componentToPrint;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        // Check if this is the last page to print
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Set up the Graphics object
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Render the component to the printable area
        componentToPrint.printAll(g);

        // Return that the page is printable
        return PAGE_EXISTS;
    }
}