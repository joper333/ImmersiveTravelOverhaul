package com.joper333.sextant.viator;

import com.joper333.sextant.multi_katometer.MultiKatometer_Model;
import com.joper333.sextant.multi_katometer.MultiKatometer_item;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Viator_Renderer extends GeoItemRenderer<Viator_item>
{
    public Viator_Renderer() {super(new Viator_Model());}
}
