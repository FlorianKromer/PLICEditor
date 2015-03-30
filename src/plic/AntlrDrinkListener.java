package plic;

import plic.HelloParser.DrinkContext;

public class AntlrDrinkListener extends HelloBaseListener {
	 
    @Override
    public void enterDrink(DrinkContext ctx) {
        System.out.println(ctx.getText());
    }
 
}