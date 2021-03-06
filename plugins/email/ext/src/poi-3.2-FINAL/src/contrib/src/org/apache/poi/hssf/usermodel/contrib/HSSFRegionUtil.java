/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.hssf.usermodel.contrib;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.Region;

/**
 * Various utility functions that make working with a region of cells easier.
 * 
 * @author Eric Pugh epugh@upstate.com
 */
public final class HSSFRegionUtil {

	private HSSFRegionUtil() {
		// no instances of this class
	}
	/**
	 * For setting the same property on many cells to the same value
	 */
	private static final class CellPropertySetter {

		private final HSSFWorkbook _workbook;
		private final String _propertyName;
		private final Short _propertyValue;

		public CellPropertySetter(HSSFWorkbook workbook, String propertyName, int value) {
			_workbook = workbook;
			_propertyName = propertyName;
			_propertyValue = new Short((short)value);
		}
		public void setProperty(HSSFRow row, int column) {
			HSSFCell cell = HSSFCellUtil.getCell(row, column);
			HSSFCellUtil.setCellStyleProperty(cell, _workbook, _propertyName, _propertyValue);
		}
	}

	private static CellRangeAddress toCRA(Region region) {
		return Region.convertToCellRangeAddress(region);
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setBorderLeft(short border, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setBorderLeft(border, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the left border for a region of cells by manipulating the cell style
	 * of the individual cells on the left
	 * 
	 * @param border The new border
	 * @param region The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setBorderLeft(int border, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int rowStart = region.getFirstRow();
		int rowEnd = region.getLastRow();
		int column = region.getFirstColumn();

		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.BORDER_LEFT, border);
		for (int i = rowStart; i <= rowEnd; i++) {
			cps.setProperty(HSSFCellUtil.getRow(i, sheet), column);
		}
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setLeftBorderColor(short color, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setLeftBorderColor(color, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the leftBorderColor attribute of the HSSFRegionUtil object
	 * 
	 * @param color The color of the border
	 * @param region The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setLeftBorderColor(int color, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int rowStart = region.getFirstRow();
		int rowEnd = region.getLastRow();
		int column = region.getFirstColumn();

		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.LEFT_BORDER_COLOR, color);
		for (int i = rowStart; i <= rowEnd; i++) {
			cps.setProperty(HSSFCellUtil.getRow(i, sheet), column);
		}
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setBorderRight(short border, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setBorderRight(border, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the borderRight attribute of the HSSFRegionUtil object
	 * 
	 * @param border The new border
	 * @param region The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setBorderRight(int border, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int rowStart = region.getFirstRow();
		int rowEnd = region.getLastRow();
		int column = region.getLastColumn();

		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.BORDER_RIGHT, border);
		for (int i = rowStart; i <= rowEnd; i++) {
			cps.setProperty(HSSFCellUtil.getRow(i, sheet), column);
		}
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setRightBorderColor(short color, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setRightBorderColor(color, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the rightBorderColor attribute of the HSSFRegionUtil object
	 * 
	 * @param color The color of the border
	 * @param region The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setRightBorderColor(int color, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int rowStart = region.getFirstRow();
		int rowEnd = region.getLastRow();
		int column = region.getLastColumn();

		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.RIGHT_BORDER_COLOR, color);
		for (int i = rowStart; i <= rowEnd; i++) {
			cps.setProperty(HSSFCellUtil.getRow(i, sheet), column);
		}
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setBorderBottom(short border, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setBorderBottom(border, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the borderBottom attribute of the HSSFRegionUtil object
	 * 
	 * @param border The new border
	 * @param region The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setBorderBottom(int border, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int colStart = region.getFirstColumn();
		int colEnd = region.getLastColumn();
		int rowIndex = region.getLastRow();
		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.BORDER_BOTTOM, border);
		HSSFRow row = HSSFCellUtil.getRow(rowIndex, sheet);
		for (int i = colStart; i <= colEnd; i++) {
			cps.setProperty(row, i);
		}
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setBottomBorderColor(short color, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setBottomBorderColor(color, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the bottomBorderColor attribute of the HSSFRegionUtil object
	 * 
	 * @param color The color of the border
	 * @param region The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setBottomBorderColor(int color, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int colStart = region.getFirstColumn();
		int colEnd = region.getLastColumn();
		int rowIndex = region.getLastRow();
		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.BOTTOM_BORDER_COLOR, color);
		HSSFRow row = HSSFCellUtil.getRow(rowIndex, sheet);
		for (int i = colStart; i <= colEnd; i++) {
			cps.setProperty(row, i);
		}
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setBorderTop(short border, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setBorderTop(border, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the borderBottom attribute of the HSSFRegionUtil object
	 * 
	 * @param border The new border
	 * @param region The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setBorderTop(int border, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int colStart = region.getFirstColumn();
		int colEnd = region.getLastColumn();
		int rowIndex = region.getFirstRow();
		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.BORDER_TOP, border);
		HSSFRow row = HSSFCellUtil.getRow(rowIndex, sheet);
		for (int i = colStart; i <= colEnd; i++) {
			cps.setProperty(row, i);
		}
	}

	/**
	 * @deprecated (Aug 2008) use {@link CellRangeAddress} instead of {@link Region}
	 */
	public static void setTopBorderColor(short color, Region region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		setTopBorderColor(color, toCRA(region), sheet, workbook);
	}
	/**
	 * Sets the topBorderColor attribute of the HSSFRegionUtil object
	 * 
	 * @param color The color of the border
	 * @param region  The region that should have the border
	 * @param workbook The workbook that the region is on.
	 * @param sheet The sheet that the region is on.
	 */
	public static void setTopBorderColor(int color, CellRangeAddress region, HSSFSheet sheet,
			HSSFWorkbook workbook) {
		int colStart = region.getFirstColumn();
		int colEnd = region.getLastColumn();
		int rowIndex = region.getFirstRow();
		CellPropertySetter cps = new CellPropertySetter(workbook, HSSFCellUtil.TOP_BORDER_COLOR, color);
		HSSFRow row = HSSFCellUtil.getRow(rowIndex, sheet);
		for (int i = colStart; i <= colEnd; i++) {
			cps.setProperty(row, i);
		}
	}
}
