package com.joper333.sextant.telosmeter;

import com.joper333.sextant.multi_katometer.MultiKatometer_item;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Telosmeter_Renderer extends GeoItemRenderer<Telosmeter_item>
{
    public Telosmeter_Renderer() {super(new Telosmeter_Model());}
}
