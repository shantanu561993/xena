/*
 * file:       GanttBarStartAndEndShapeStyle.java
 * author:     Tom Ollar
 * copyright:  (c) Packwood Software 2009
 * date:       26/03/2009
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package net.sf.mpxj.mpp;

import net.sf.mpxj.utility.MpxjEnum;
import net.sf.mpxj.utility.NumberUtility;

import java.util.EnumSet;

/**
 * Represents the style of the start and end sections of a Gantt bar.
 */
public enum GanttBarStartAndEndShapeStyle implements MpxjEnum
{
   SOLID(0, "Solid"),
   FRAMED(1, "Framed"),
   DASHED(2, "Dashed");

   /**
    * Private constructor.
    *
    * @param type int version of the enum
    * @param name name of the enum
    */
   private GanttBarStartAndEndShapeStyle(int type, String name)
   {
      m_value = type;
      m_name = name;
   }

   /**
    * Retrieve an instance of the enum based on its int value.
    *
    * @param type int type
    * @return enum instance
    */
   public static GanttBarStartAndEndShapeStyle getInstance(int type)
   {
      if (type < 0 || type >= TYPE_VALUES.length)
      {
         type = SOLID.getValue();
      }
      return (TYPE_VALUES[type]);
   }

   /**
    * Retrieve an instance of the enum based on its int value.
    *
    * @param type int type
    * @return enum instance
    */
   public static GanttBarStartAndEndShapeStyle getInstance(Number type)
   {
      int value;
      if (type == null)
      {
         value = -1;
      }
      else
      {
         value = NumberUtility.getInt(type);
      }
      return (getInstance(value));
   }

   /**
    * Accessor method used to retrieve the numeric representation of the enum.
    *
    * @return int representation of the enum
    */
   public int getValue()
   {
      return (m_value);
   }

   /**
    * Retrieve the line style name. Currently this is not localised.
    *
    * @return style name
    */
   public String getName()
   {
      return (m_name);
   }

   /**
    * Retrieve the String representation of this line style.
    *
    * @return String representation of this line style
    */
   @Override public String toString()
   {
      return (getName());
   }

   /**
    * Array mapping int types to enums.
    */
   private static final GanttBarStartAndEndShapeStyle[] TYPE_VALUES = new GanttBarStartAndEndShapeStyle[3];
   static
   {
      for (GanttBarStartAndEndShapeStyle e : EnumSet.range(GanttBarStartAndEndShapeStyle.SOLID, GanttBarStartAndEndShapeStyle.DASHED))
      {
         TYPE_VALUES[e.getValue()] = e;
      }
   }

   /**
    * Internal representation of the enum int type.
    */
   private int m_value;
   private String m_name;
}
