/**
 *  @author : Paul Taylor
 *  @author : Eric Farng
 *
 *  Version @version:$Id: Lyrics3Image.java,v 1.6 2007/11/29 12:05:26 paultaylor Exp $
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
package com.hadeslee.audiotag.tag.datatype;

import java.util.Arrays;

import com.hadeslee.audiotag.tag.id3.AbstractTagFrameBody;
import com.hadeslee.audiotag.tag.InvalidDataTypeException;
import com.hadeslee.audiotag.audio.generic.Utils;

public class Lyrics3Image extends AbstractDataType
{
    /**
     * 
     */
    private Lyrics3TimeStamp time = null;

    /**
     * 
     */
    private String description = "";

    /**
     * 
     */
    private String filename = "";

    /**
     * Creates a new ObjectLyrics3Image datatype.
     *
     * @param identifier 
     */
    public Lyrics3Image(String identifier, AbstractTagFrameBody frameBody)
    {
        super(identifier, frameBody);
    }

    public Lyrics3Image(Lyrics3Image copy)
    {
        super(copy);
        this.time = new Lyrics3TimeStamp(copy.time);
        this.description = new String(copy.description);
        this.filename = new String(copy.filename);
    }

    /**
     * 
     *
     * @param description 
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * 
     *
     * @return 
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * 
     *
     * @param filename 
     */
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    /**
     * 
     *
     * @return 
     */
    public String getFilename()
    {
        return this.filename;
    }

    /**
     * 
     *
     * @return 
     */
    public int getSize()
    {
        int size;

        size = filename.length() + 2 + description.length() + 2;

        if (time != null)
        {
            size += time.getSize();
        }

        return size;
    }

    /**
     * 
     *
     * @param time 
     */
    public void setTimeStamp(Lyrics3TimeStamp time)
    {
        this.time = time;
    }

    /**
     * 
     *
     * @return 
     */
    public Lyrics3TimeStamp getTimeStamp()
    {
        return this.time;
    }

    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

    /**
     * 
     *
     * @param obj 
     * @return 
     */
    public boolean equals(Object obj)
    {
        if ((obj instanceof Lyrics3Image) == false)
        {
            return false;
        }

        Lyrics3Image object = (Lyrics3Image) obj;

        if (this.description.equals(object.description) == false)
        {
            return false;
        }

        if (this.filename.equals(object.filename) == false)
        {
            return false;
        }

        if (this.time == null)
        {
            if (object.time != null)
            {
                return false;
            }
        }
        else
        {
            if (this.time.equals(object.time) == false)
            {
                return false;
            }
        }

        return super.equals(obj);
    }

	/**
     * 
     *
     * @param imageString 
     * @param offset      
     * @throws NullPointerException      
     * @throws IndexOutOfBoundsException 
     */
    public void readString(String imageString, int offset)
    {
        if (imageString == null)
        {
            throw new NullPointerException("Image string is null");
        }

        if ((offset < 0) || (offset >= imageString.length()))
        {
            throw new IndexOutOfBoundsException("Offset to image string is out of bounds: offset = " + offset + ", string.length()" + imageString.length());
        }

        if (imageString != null)
        {
            String timestamp;
            int delim;

            delim = imageString.indexOf("||", offset);
            filename = imageString.substring(offset, delim);

            offset = delim + 2;
            delim = imageString.indexOf("||", offset);
            description = imageString.substring(offset, delim);

            offset = delim + 2;
            timestamp = imageString.substring(offset);

            if (timestamp.length() == 7)
            {
                time = new Lyrics3TimeStamp("Time Stamp");
                time.readString(timestamp);
            }
        }
    }

    /**
     * 
     *
     * @return 
     */
    public String toString()
    {
        String str;
        str = "filename = " + filename + ", description = " + description;

        if (time != null)
        {
            str += (", timestamp = " + time.toString());
        }

        return str + "\n";
    }

    /**
     * 
     *
     * @return 
     */
    public String writeString()
    {
        String str = "";

        if (filename == null)
        {
            str = "||";
        }
        else
        {
            str = filename + "||";
        }

        if (description == null)
        {
            str += "||";
        }
        else
        {
            str += (description + "||");
        }

        if (time != null)
        {
            str += time.writeString();
        }

        return str;
    }

    public void readByteArray(byte[] arr, int offset) throws InvalidDataTypeException
    {
        readString(Arrays.toString(arr), offset);
    }

    public byte[] writeByteArray()
    {
        return Utils.getDefaultBytes(writeString(),"ISO-8859-1");
    }

}
