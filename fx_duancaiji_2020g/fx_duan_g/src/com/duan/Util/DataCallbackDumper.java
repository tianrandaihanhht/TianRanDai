package com.duan.Util;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;

public class DataCallbackDumper implements DataCallback
{

    public void changed (final Item item, final ItemState itemState )
    {
        try {
            int type=itemState.getValue().getType();
        } catch (JIException e) {
            e.printStackTrace();
        }
        System.out.println ( String.format ( "Item: %s, Value: %s, Timestamp: %tc, Quality: %d", item.getId (), itemState.getValue (), itemState.getTimestamp (), itemState.getQuality () ) );

        try
        {
            VariantDumper.dumpValue ( "\t", itemState.getValue () );
        }
        catch ( final JIException e )
        {
            e.printStackTrace ();
        }

    }

}