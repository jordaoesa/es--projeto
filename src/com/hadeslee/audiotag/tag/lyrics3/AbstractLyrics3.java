/**
 *  @author : Paul Taylor
 *  @author : Eric Farng
 *
 *  Version @version:$Id: AbstractLyrics3.java,v 1.7 2007/08/07 14:36:16 paultaylor Exp $
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
 */
package com.hadeslee.audiotag.tag.lyrics3;

import com.hadeslee.audiotag.tag.id3.AbstractTag;
import com.hadeslee.audiotag.tag.id3.ID3v1Tag;

import java.io.IOException;
import java.io.RandomAccessFile;


public abstract class AbstractLyrics3 extends AbstractTag
{
    public AbstractLyrics3()
    {
    }

    public AbstractLyrics3(AbstractLyrics3 copyObject)
    {
        super(copyObject);
    }

    /**
     * 
     *
     * @param file 
     * @throws IOException 
     */
    public void delete(RandomAccessFile file)
        throws IOException
    {
    	//pass
    }
}
