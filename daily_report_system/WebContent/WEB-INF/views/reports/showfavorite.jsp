<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${favorite != null}">
                <h2>日報 詳細ページ</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${favorite.report.employee.name}" /></td>
                        </tr>
                    </tbody>
                     <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${favorite.report.report_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                         <tr>
                            <th>内容</th>
                            <td>

                                <pre><c:out value="${favorite.report.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${favorite.report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${favorite.report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>追加日時</th>
                            <td>
                                <fmt:formatDate value="${favorite.added_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                </table>
                <br>
                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <p><a href="<c:url value='/reports/edit?id=${report.id}' />">この日報を編集する</a></p>
                </c:if>
                <p><a href="#" onclick="confirmDestroy();">このメッセージを削除する</a></p>
                <form method="POST" action="${pageContext.request.contextPath}/reports/destroy">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                            document.forms[0].submit();
                        }
                    }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <p><a href="<c:url value='/reports/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>