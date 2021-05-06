package pe.com.sedapal.seguridad.core.bean;

import java.util.List;

public class JQueryDataTableParamBean {    
   
	/// <summary>
    /// A full result, as understood by jQuery DataTables.
    /// </summary>
    /// <typeparam name="T">The data type of each row.</typeparam>
    public class DTResult<T>
    {
        /// <summary>
        /// The draw counter that this object is a response to - from the draw parameter sent as part of the data request.
        /// Note that it is strongly recommended for security reasons that you cast this parameter to an integer, rather than simply echoing back to the client what it sent in the draw parameter, in order to prevent Cross Site Scripting (XSS) attacks.
        /// </summary>
        public int draw;
        
        public int getDraw() {
			return draw;
		}
		public void setDraw(int draw) {
			this.draw = draw;
		}
		
        /// <summary>
        /// Total records, before filtering (i.e. the total number of records in the database)
        /// </summary>
        public int recordsTotal;
        
        public int getRecordsTotal() {
			return recordsTotal;
		}
		public void setRecordsTotal(int recordsTotal) {
			this.recordsTotal = recordsTotal;
		}
		
        /// <summary>
        /// Total records, after filtering (i.e. the total number of records after filtering has been applied - not just the number of records being returned for this page of data).
        /// </summary>
        public int recordsFiltered;
        
        public int getRecordsFiltered() {
			return recordsFiltered;
		}
		public void setRecordsFiltered(int recordsFiltered) {
			this.recordsFiltered = recordsFiltered;
		}
		
        /// <summary>
        /// The data to be displayed in the table.
        /// This is an array of data source objects, one for each row, which will be used by DataTables.
        /// Note that this parameter's name can be changed using the ajaxDT option's dataSrc property.
        /// </summary>
		public List<T> data;
		
		public List<T> getData() {
			return data;
		}
		public void setData(List<T> data) {
			this.data = data;
		}
    }
   
    
    /// <summary>
    /// The additional columns that you can send to jQuery DataTables for automatic processing.
    /// </summary>
    public abstract class DTRow
    {
        /// <summary>
        /// Set the ID property of the dt-tag tr node to this value
        /// </summary>
    	public String DT_RowId;
    	
        public String getDT_RowId() {
			return null;
		}
        
        /// <summary>
        /// Add this class to the dt-tag tr node
        /// </summary>
        public String DT_RowClass;

        public String getDT_RowClass() {
			return null;
		}
        
        /// <summary>
        /// Add this data property to the row's dt-tag tr node allowing abstract data to be added to the node, using the HTML5 data-* attributes.
        /// This uses the jQuery data() method to set the data, which can also then be used for later retrieval (for example on a click event).
        /// </summary>
        public Object DT_RowData;
        
        public Object getDT_RowData() {
			return null;
		}
        
    }

    
    /// <summary>
    /// The parameters sent by jQuery DataTables in AJAX queries.
    /// </summary>
    public class DTParameters
    {
    	public DTParameters(){
    	}
    	/// <summary>
        /// Draw counter.
        /// This is used by DataTables to ensure that the Ajax returns from server-side processing requests are drawn in sequence by DataTables (Ajax requests are asynchronous and thus can return out of sequence).
        /// This is used as part of the draw return parameter (see below).
        /// </summary>
        public int Draw;
        
        public int getDraw() {
			return Draw;
		}
		public void setDraw(int draw) {
			Draw = draw;
		}
		
		/// <summary>
        /// An array defining all columns in the table.
        /// </summary>
        public DTColumn[] Columns;
        
        public DTColumn[] getColumns() {
			return Columns;
		}
		public void setColumns(DTColumn[] columns) {
			Columns = columns;
		}
		
		/// <summary>
        /// An array defining how many columns are being ordering upon - i.e. if the array length is 1, then a single column sort is being performed, otherwise a multi-column sort is being performed.
        /// </summary>
        public DTOrder[] Order;
        
        public DTOrder[] getOrder() {
			return Order;
		}
		public void setOrder(DTOrder[] order) {
			Order = order;
		}
		
        /// <summary>
        /// Paging first record indicator.
        /// This is the start point in the current data set (0 index based - i.e. 0 is the first record).
        /// </summary>
		public int Start;

        public int getStart() {
			return Start;
		}
		public void setStart(int start) {
			Start = start;
		}
		
		/// <summary>
        /// Number of records that the table can display in the current draw.
        /// It is expected that the number of records returned will be equal to this number, unless the server has fewer records to return.
        /// Note that this can be -1 to indicate that all records should be returned (although that negates any benefits of server-side processing!)
        /// </summary>
        public int Length;

        public int getLength() {
			return Length;
		}
		public void setLength(int length) {
			Length = length;
		}
		
		/// <summary>
        /// Search value. To be applied to all columns which have searchable as true.
        /// </summary>
		public String Search;
		
		public String getSearch() {
			return Search;
		}
		public void setSearch(String search) {
			Search = search;
		}
		
		/// <summary>
        /// Global search value. To be applied to all columns which have searchable as true.
        /// </summary>
        /*public DTSearch Search;
        
        public DTSearch getSearch() {
			return Search;
		}
		public void setSearch(DTSearch search) {
			Search = search;
		}*/
		
        /// <summary>
        /// Custom column that is used to further sort on the first Order column.
        /// </summary>
		public String SortOrder;
		
		public String getSortOrder() {
			return Columns != null && Order != null && Order.length > 0
                    ? (Columns[Order[0].Column].Data + (Order[0].Dir == DTOrderDir.DESC ? " " + Order[0].Dir : ""))
                    : null;
		}
	
    }
    
    
    /// <summary>
    /// A jQuery DataTables column.
    /// </summary>
    public class DTColumn
    {
        /// <summary>
        /// Column's data source, as defined by columns.data.
        /// </summary>
        public String Data;
        
        public String getData() {
			return Data;
		}

		public void setData(String data) {
			Data = data;
		}

		/// <summary>
        /// Column's name, as defined by columns.name.
        /// </summary>
        public String Name;
        
        public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		/// <summary>
        /// Flag to indicate if this column is searchable (true) or not (false). This is controlled by columns.searchable.
        /// </summary>
        public boolean Searchable;
        
        public boolean isSearchable() {
			return Searchable;
		}

		public void setSearchable(boolean searchable) {
			Searchable = searchable;
		}

		/// <summary>
        /// Flag to indicate if this column is orderable (true) or not (false). This is controlled by columns.orderable.
        /// </summary>
        public boolean Orderable;
        
        public boolean isOrderable() {
			return Orderable;
		}

		public void setOrderable(boolean orderable) {
			Orderable = orderable;
		}

		/// <summary>
        /// Specific search value.
        /// </summary>
        public DTSearch Search;

		public DTSearch getSearch() {
			return Search;
		}

		public void setSearch(DTSearch search) {
			Search = search;
		}
        
    }
    
    
    /// <summary>
    /// An order, as sent by jQuery DataTables when doing AJAX queries.
    /// </summary>
    public class DTOrder
    {
        /// <summary>
        /// Column to which ordering should be applied.
        /// This is an index reference to the columns array of information that is also submitted to the server.
        /// </summary>
        public int Column;
        
        public int getColumn() {
			return Column;
		}

		public void setColumn(int column) {
			Column = column;
		}

		/// <summary>
        /// Ordering direction for this column.
        /// It will be dt-string asc or dt-string desc to indicate ascending ordering or descending ordering, respectively.
        /// </summary>
        public DTOrderDir Dir;

		public DTOrderDir getDir() {
			return Dir;
		}

		public void setDir(DTOrderDir dir) {
			Dir = dir;
		}
        
    }

    /// <summary>
    /// Sort orders of jQuery DataTables.
    /// </summary>
    public enum DTOrderDir
    {
        ASC,
        DESC
    }
    
	/// <summary>
	/// A search, as sent by jQuery DataTables when doing AJAX queries.
	/// </summary>
	public class DTSearch
	{
	    /// <summary>
	    /// Global search value. To be applied to all columns which have searchable as true.
	    /// </summary>
	    public String Value;
	    
	    public String getValue() {
			return Value;
		}

		public void setValue(String value) {
			Value = value;
		}

		/// <summary>
	    /// true if the global filter should be treated as a regular expression for advanced searching, false otherwise.
	    /// Note that normally server-side processing scripts will not perform regular expression searching for performance reasons on large data sets, but it is technically possible and at the discretion of your script.
	    /// </summary>
	    public boolean Regex;
	    
		public boolean isRegex() {
			return Regex;
		}
		public void setRegex(boolean regex) {
			Regex = regex;
		}
	    
	    
	}
    
    
    
}

