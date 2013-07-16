import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.noggit.JSONUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

/**
 * Servlet implementation class EjemploServlet
 */
@WebServlet("/EjemploServlet")
public class EjemploServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String CONTENT_TYPE = null;
    /**
     * @see HttpServlet#HttpServlet()
     */	
    public EjemploServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unused" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String busqueda = request.getParameter("busqueda");
		
	    SolrServer server = new HttpSolrServer("http://localhost:8983/solr/");
	    
	    try {
	    	server.deleteByQuery( "*:*" );// CAUTION: deletes everything!
	    	
		    URL url1 = new URL("http://localhost:8983/solr/dataimport?command=full-import");
            url1.openStream();
            
	       	SolrServer serv = getSolrServer();
		    SolrQuery query = new SolrQuery();
		    query.setQuery( busqueda );  
		    QueryResponse rsp = null;
			rsp = server.query( query );
			SolrDocumentList doc = rsp.getResults();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			out.print(JSONUtil.toJSON(doc));
			out.flush();
			out.close();
			
	    }
	    catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void Out(String string) {
		// TODO Auto-generated method stub
		
	}

	private SolrServer getSolrServer() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
