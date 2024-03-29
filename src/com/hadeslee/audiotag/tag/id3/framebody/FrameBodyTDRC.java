/**
 *  @author : Paul Taylor
 *  @author : Eric Farng
 *
 *  Version @version:$Id: FrameBodyTDRC.java,v 1.12 2007/08/06 16:04:34 paultaylor Exp $
 *
 *  MusicTag Copyright (C)2003,2004
 *
 *  This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
 *  General Public  License as published by the Free Software Foundation; either version 2.1 of the License,
 *  or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 *  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License along with this library; if not,
 *  you can get a copy from http://www.opensource.org/licenses/lgpl-license.php or write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Description:
 *
 */
package com.hadeslee.audiotag.tag.id3.framebody;

import com.hadeslee.audiotag.tag.InvalidTagException;
import com.hadeslee.audiotag.tag.datatype.DataTypes;
import com.hadeslee.audiotag.tag.id3.ID3v23Frames;
import com.hadeslee.audiotag.tag.id3.ID3v24Frames;
import com.hadeslee.audiotag.tag.id3.valuepair.TextEncoding;

import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FrameBodyTDRC extends AbstractFrameBodyTextInfo implements ID3v24FrameBody
{
    /**
     * Used when converting from v3 tags
     */
    private String originalID;
    private String year = "";
    private String time = "";
    private String date = "";
    private String reco = "";

    private static final int PRECISION_SECOND = 0;
    private static final int PRECISION_MINUTE = 1;
    private static final int PRECISION_HOUR = 2;
    private static final int PRECISION_DAY = 3;
    private static final int PRECISION_MONTH = 4;
    private static final int PRECISION_YEAR = 5;

    /**
     * Creates a new FrameBodyTDRC datatype.
     */
    public FrameBodyTDRC()
    {
        super();
    }

    public FrameBodyTDRC(FrameBodyTDRC body)
    {
        super(body);
    }

    /**
     * Retrieve the original identifier
     */
    public String getOriginalID()
    {
        return originalID;
    }

    /**
     * When this has been generated as an amalgamation of v3 frames assumes
     * the v3 frames match the the format in specification and convert them
     * to their equivalent v4 format and return the generated String.
     * i.e if the v3 frames contain a valid value this will return a valid
     * v4 value, if not this won't.
     */

    public String getFormattedText()
    {
        StringBuffer sb = new StringBuffer();
        if (originalID == null)
        {
            return this.getText();
        }
        else
        {
            if (year != null && !(year.equals("")))
            {
                try
                {
                    sb.append((new SimpleDateFormat("yyyy")).format((new SimpleDateFormat("yyyy")).parse(year)));
                }
                catch (ParseException e)
                {
                    logger.warning("Unable to parse:" + year + " as year");
                }
            }
            if (!date.equals(""))
            {
                try
                {
                    sb.append((new SimpleDateFormat("-MM-dd")).format((new SimpleDateFormat("ddMM")).parse(date)));
                }
                catch (ParseException e)
                {
                     logger.warning("Unable to parse:" + date + " as date");
                }
            }
            if (!time.equals(""))
            {
                try
                {
                    sb.append((new SimpleDateFormat("'T'HH:mm")).format((new SimpleDateFormat("HHmm")).parse(time)));
                }
                catch (ParseException e)
                {
                     logger.warning("Unable to parse:" + time + " as time");
                }
            }
            return sb.toString();
        }
    }

    public void setYear(String year)
    {
        logger.finest("Setting year to" + year);
        this.year = year;
    }

    public void setTime(String time)
    {
        logger.finest("Setting time to:" + time);
        this.time = time;
    }

    public void setDate(String date)
    {
        logger.finest("Setting date to:" + date);

        this.date = date;
    }

    public void setReco(String reco)
    {
        this.reco = reco;
    }

    public String getYear()
    {
        return year;
    }

    public String getTime()
    {
        return time;
    }

    public String getDate()
    {
        return date;
    }

    public String getReco()
    {
        return reco;
    }

    /**
     * When converting v3 YEAR to v4 TDRC frame
     */
    public FrameBodyTDRC(FrameBodyTYER body)
    {
        originalID = ID3v23Frames.FRAME_ID_V3_TYER;
        year = body.getText();
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, TextEncoding.ISO_8859_1);
        setObjectValue(DataTypes.OBJ_TEXT, body.getText());
    }

    /**
     * When converting v3 TIME to v4 TDRC frame
     */
    public FrameBodyTDRC(FrameBodyTIME body)
    {
        originalID = ID3v23Frames.FRAME_ID_V3_TIME;
        time = body.getText();
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, TextEncoding.ISO_8859_1);
        setObjectValue(DataTypes.OBJ_TEXT, body.getText());
    }

    /**
     * When converting v3 TDAT to v4 TDRC frame
     */
    public FrameBodyTDRC(FrameBodyTDAT body)
    {
        originalID = ID3v23Frames.FRAME_ID_V3_TDAT;
        date = body.getText();
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, TextEncoding.ISO_8859_1);
        setObjectValue(DataTypes.OBJ_TEXT, body.getText());
    }

    /**
     * When converting v3 TRDA to v4 TDRC frame
     */
    public FrameBodyTDRC(FrameBodyTRDA body)
    {
        originalID = ID3v23Frames.FRAME_ID_V3_TRDA;
        reco = body.getText();
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, TextEncoding.ISO_8859_1);
        setObjectValue(DataTypes.OBJ_TEXT, body.getText());
    }

    /**
     * Creates a new FrameBodyTDRC datatype.
     *
     * @param textEncoding
     * @param text
     */
    public FrameBodyTDRC(byte textEncoding, String text)
    {
        super(textEncoding, text);
        List<SimpleDateFormat> formatters = new ArrayList<SimpleDateFormat>();
    	initFormaters(formatters);
        //Find the date format of the text
        for (int i = 0; i < formatters.size(); i++)
        {
            try
            {
                final Date d = ((SimpleDateFormat) formatters.get(i)).parse(getText());
                if (d != null)
                {
                    extractID3v23Formats(d, i);
                    break;
                }
            }
                //Dont display will occur for each failed format
            catch (ParseException e)
            {
                //Do nothing;
            }
        }
    }

    /**
     * Creates a new FrameBodyTDRC datatype from File
     *
     * @throws InvalidTagException
     */
    public FrameBodyTDRC(ByteBuffer byteBuffer, int frameSize)
        throws InvalidTagException
    {
    	
    	super(byteBuffer, frameSize);
    	List<SimpleDateFormat> formatters = new ArrayList<SimpleDateFormat>();
    	initFormaters(formatters);
        //Store the equivalent ID3v23 values in case convert

        //Find the date format of the v24Frame
        for (int i = 0; i < formatters.size(); i++)
        {
            try
            {
                final Date d = ((SimpleDateFormat) formatters.get(i)).parse(getText());
                if (d != null)
                {
                    extractID3v23Formats(d, i);
                    break;
                }
            }
                //Dont display will occur for each failed format
            catch (ParseException e)
            {
                //Do nothing;
            }
        }
    }

    private void extractID3v23Formats(final Date dateRecord, final int precision)
    {
        Date d = dateRecord;

        //Precision Year
        if (precision == PRECISION_YEAR)
        {
            setYear((new SimpleDateFormat("yyyy")).format(d));
        }
        //Precision Month
        else if (precision == PRECISION_MONTH)
        {
            setYear((new SimpleDateFormat("yyyy")).format(d));
        }
        //Precision Day
        else if (precision == PRECISION_DAY)
        {
            setYear((new SimpleDateFormat("yyyy")).format(d));
            setDate((new SimpleDateFormat("-MM-dd")).format(d));
        }
        //Precision Hour
        else if (precision == PRECISION_HOUR)
        {
            setYear((new SimpleDateFormat("yyyy")).format(d));
            setDate((new SimpleDateFormat("-MM-dd")).format(d));

        }
        //Precision Minute
        else if (precision == PRECISION_MINUTE)
        {
            setYear((new SimpleDateFormat("yyyy")).format(d));
            setDate((new SimpleDateFormat("-MM-dd")).format(d));
            setTime((new SimpleDateFormat("'T'HH:mm")).format(d));
        }
        //Precision Minute
        else if (precision == PRECISION_SECOND)
        {
            setYear((new SimpleDateFormat("yyyy")).format(d));
            setDate((new SimpleDateFormat("-MM-dd")).format(d));
            setTime((new SimpleDateFormat("'T'HH:mm")).format(d));
        }
    }
    
    private void initFormaters(List<SimpleDateFormat> formatters) {
    	formatters.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        formatters.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"));
        formatters.add(new SimpleDateFormat("yyyy-MM-dd'T'HH"));
        formatters.add(new SimpleDateFormat("yyyy-MM-dd"));
        formatters.add(new SimpleDateFormat("yyyy-MM"));
        formatters.add(new SimpleDateFormat("yyyy"));
	}

     /**
      * The ID3v2 frame identifier
      *
      * @return the ID3v2 frame identifier  for this frame type
     */
    public String getIdentifier()
    {
        return ID3v24Frames.FRAME_ID_YEAR;
    }
}
