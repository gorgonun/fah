package catalog.crawlers
import catalog.pojos.QARaw
import scalaj.http.Http
import org.json4s._
import org.json4s.native.JsonMethods._

object QACrawler {

  def main(args: Array[String]): Unit ={
    implicit val formats = DefaultFormats
    val url="https://www.quintoandar.com.br/api/search?q=for_rent:%27true%27&fq=local:[%27-27.553723535794024,-48.56940561570027%27,%27-27.643555064205977,-48.46804018429974%27]&return=banheiros,quartos,iptu,andar,endereco,amenidades,tipo,instalacoes,aluguel_condominio,for_rent,local,suites,bairro,aluguel,foto_capa,photos,home_insurance,condominio,ultima_publicacao,variant_images_titles,variant_images,custo,cidade,visit_status,garantias,for_sale,first_publication,condo_iptu,vagas&start=0&size=40&q.parser=structured&format=json&sort=ultima_publicacao%20desc"
    val jsonResp = parse(Http(url).asString.body)
    val r = jsonResp.extract[List[QARaw]]
    println(r)
  }
}