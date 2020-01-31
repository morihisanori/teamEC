<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/latte.css">
<link rel="stylesheet" href="./css/image.css">
<link rel="stylesheet" href="./css/input.css">
<link rel="stylesheet" href="./css/table.css">
<link rel="stylesheet" href="./css/message.css">


<title>商品詳細</title>
</head>

<body>
<div id="header">
	<jsp:include page="header.jsp" />
</div>
	<div id="contents">
		<h1>商品詳細画面</h1>
		<s:if test="productInfoDTO!=null">
			<s:form action="AddCartAction">
				<div class="box">
					<div class="2column-container">


						<div class="left">
							<img
								src='<s:property value="productInfoDTO.imageFilePath"/>/<s:property value="productInfoDTO.imageFileName"/>'
								class="item-image-box-320" /><br>
						</div>

						<div class="right">
							<table class="r-table">
								<tr>
									<th scope="row"><s:label value="商品名" /></th>
									<td><s:property value="productInfoDTO.productName" /></td>
								</tr>
								<tr>
									<th scope="row"><s:label value="商品名ふりがな" /></th>
									<td><s:property value="productInfoDTO.productNameKana" /></td>
								</tr>
								<tr>
									<th scope="row"><s:label value="値段" /></th>
									<td><s:property value="productInfoDTO.price" />円</td>
								</tr>
								<tr>
									<th scope="row"><s:label value="購入個数" /></th>
									<td><s:select name="productCount"
											list="%{productCountList}" />個</td>
								</tr>
								<tr>
									<th scope="row"><s:label value="発売会社名" /></th>
									<td><s:property value="productInfoDTO.releaseCompany" /></td>
								</tr>
								<tr>
									<th scope="row"><s:label value="発売年月日" /></th>
									<td><s:property value="productInfoDTO.releaseDate" /></td>
								</tr>
								<tr>
									<th scope="row"><s:label value="商品詳細情報" /></th>
									<td><s:property value="productInfoDTO.productDescription" /></td>
								</tr>
							</table>
							<s:hidden name="productId" value="%{productInfoDTO.productId}" />
								<s:submit value="カートに追加" class="submit_btn_2" />
						</div>
					</div>
				</div>
			</s:form>
			<s:if test="relatedProductList!=null && relatedProductList.size()>0">
				<div class="box">
				<br><br><h2>【関連商品】</h2><br><br>
					<div class="product-details-recomｍend-box">
						<s:iterator value="relatedProductList">
							<div class="recommend-box">
								<a
									href='<s:url action="ProductDetailsAction">
					<s:param name="productId" value="%{productId}"/>
					</s:url>'><img
									src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
									class="item-image-box-100" /> <s:property value="productName" /><br>
								</a>
							</div>
						</s:iterator>
					</div>
				</div>
			</s:if>
		</s:if>
		<s:else>
			<div class="error">商品の詳細情報がありません。</div>
		</s:else>
	</div>
</body>
</html>