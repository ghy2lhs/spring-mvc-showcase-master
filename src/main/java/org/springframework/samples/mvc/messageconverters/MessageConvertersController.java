package org.springframework.samples.mvc.messageconverters;

import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/messageconverters")
public class MessageConvertersController {
	/**
	 * StringHttpMessageConverter
	 * Request URL:http://localhost:8080/messageconverters/string;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 * Request Payload 中包含有：foo
	 * @param string
	 * @return
	 */
	@RequestMapping(value="/string", method=RequestMethod.POST)
	public @ResponseBody String readString(@RequestBody String string) {
		return "Read string '" + string + "'";
	}

	/**
	 * Request URL:http://localhost:8080/messageconverters/string;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 * @return
	 */
	@RequestMapping(value="/string", method=RequestMethod.GET)
	public @ResponseBody String writeString() {
		return "Wrote a string";
	}

	// Form encoded data (application/x-www-form-urlencoded)

	/**
	 * Request URL:http://localhost:8080/messageconverters/form;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 * Form Data中包含有：foo=bar&fruit=apple
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public @ResponseBody String readForm(@ModelAttribute JavaBean bean) {
		return "Read x-www-form-urlencoded: " + bean;
	}

	/**
	 * 写表单数据
	 * Request URL:http://localhost:8080/messageconverters/form;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 * foo=bar&fruit=apple
	 * @return
	 */
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public @ResponseBody MultiValueMap<String, String> writeForm() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("foo", "bar");
		map.add("fruit", "apple");
		return map;
	}

	// Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)

	/**
	 *  读取xml文件
	 *  Request URL:http://localhost:8080/messageconverters/xml;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 *  Content-Type:application/xml
	 *  Request Payload : <javaBean><foo>bar</foo><fruit>apple</fruit></javaBean>
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/xml", method=RequestMethod.POST)
	public @ResponseBody String readXml(@RequestBody JavaBean bean) {
		return "Read from XML: " + bean;
	}

	/**
	 * Request URL:http://localhost:8080/messageconverters/xml;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 * Content-Type:application/xml
	 * @return
	 */
	@RequestMapping(value="/xml", method=RequestMethod.GET)
	public @ResponseBody JavaBean writeXml() {
		return new JavaBean("bar", "apple");
	}

	// MappingJacksonHttpMessageConverter (requires Jackson on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)

	/**
	 * 读取有效的json文件
	 * Request URL:http://localhost:8080/messageconverters/json;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 * Content-Type:application/json
	 * Request Payload中包含有：  { "foo": "bar", "fruit": "apple" }
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/json", method=RequestMethod.POST)
	public @ResponseBody String readJson(@Valid @RequestBody JavaBean bean) {
		return "Read from JSON: " + bean;
	}

	/**
	 * 读取无效的json文件
	 * Request URL:http://localhost:8080/messageconverters/json;jsessionid=C11BB507CC30385C884C95EF27EDF2F4
	 * Request Payload中包含有：{ "foo": "bar" }
	 * @return
	 */
	@RequestMapping(value="/json", method=RequestMethod.GET)
	public @ResponseBody JavaBean writeJson() {
		return new JavaBean("bar", "apple");
	}

	// AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)

	/**
	 * Request URL:http://localhost:8080/messageconverters/atom
	 * Request Method:POST
	 * Content-Type:application/atom+xml
	 * Request Payload: <feed xmlns="http://www.w3.org/2005/Atom"><title>My Atom feed</title></feed>
	 * @param feed
	 * @return
	 */
	@RequestMapping(value="/atom", method=RequestMethod.POST)
	public @ResponseBody String readFeed(@RequestBody Feed feed) {
		return "Read " + feed.getTitle();
	}

	/**
	 * Request URL:http://localhost:8080/messageconverters/atom
	 * Request Method:GET
	 * Accept:application/atom+xml
	 * Response:
	 *     <feed xmlns="http://www.w3.org/2005/Atom">
	 * @ret  <title>My Atom feed</title>urn</feed>
	 */
	@RequestMapping(value="/atom", method=RequestMethod.GET)
	public @ResponseBody Feed writeFeed() {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("My Atom feed");
		return feed;
	}

	// RssChannelHttpMessageConverter (requires Rome on the classpath - useful for serving RSS feeds)

	/**
	 * Request URL:http://localhost:8080/messageconverters/rss
	 * Request Method:POST
	 * Request Payload中包含有：<channel><title>My RSS feed</title></channel></rss>
	 * @param channel
	 * @return
	 */
	@RequestMapping(value="/rss", method=RequestMethod.POST)
	public @ResponseBody String readChannel(@RequestBody Channel channel) {
		return "Read " + channel.getTitle();
	}

	/**
	 *  Request URL:http://localhost:8080/messageconverters/rss
	 *  Request Method:GET
	 *  Response:
	 *      <rss version="2.0">
	 *          <channel>
	 *              <title>My RSS feed</title>
	 *              <link>http://localhost:8080/mvc-showcase/rss</link>
	 *              <description>Description</description>
	 *          </channel>
	 *      </rss>
	 * @return
	 */
	@RequestMapping(value="/rss", method=RequestMethod.GET)
	public @ResponseBody Channel writeChannel() {
		Channel channel = new Channel();
		channel.setFeedType("rss_2.0");
		channel.setTitle("My RSS feed");
		channel.setDescription("Description");
		channel.setLink("http://localhost:8080/mvc-showcase/rss");
		return channel;
	}

}