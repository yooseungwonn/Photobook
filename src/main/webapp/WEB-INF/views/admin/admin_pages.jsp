<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 대시보드</title>
<link type="text/css" rel="stylesheet"
	href='<c:url value="/css/common_style.css"/>' />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .chart-container {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
            }
        .chart-item {
            width: 48%;
        }
        @media (max-width: 768px) {
            .chart-container {
                flex-direction: column;
            }
            .chart-item {
                width: 100%;
                margin-bottom: 20px;
            }
        }
    </style>
</head>
<body>	
    <c:import url="/WEB-INF/views/admin/includes/admin_header.jsp"></c:import>

   <div class="main-content">
		<div class="header">
			<h2>대시보드</h2>
    </div>
        <div class="chart-container">
            <!-- 판매 데이터 차트 -->
            <div class="chart-item">
                <h3>판매 현황</h3>
                <canvas id="salesChart" style="width: 100%; height: 300px;"></canvas>
            </div>
            <!-- 상위 판매 앨범 차트 -->
            <div class="chart-item">
                <h3>상위 판매 앨범</h3>
                <canvas id="topAlbumsChart" style="width: 100%; height: 300px;"></canvas>
            </div>
        </div>

       <div class="card">
        <div class="card-header">
          <a href="<c:url value='/admin/Norder'/>" onclick="dashBoard1(event)">미처리
            주문 ${OrderCount}건이 있습니다</a>
        </div>
        <div class="card-header">
          <a href="<c:url value='/admin/Nshipment'/>" onclick="dashBoard2(event)">미처리 배송
            ${ShipCount}건이 있습니다</a>
        </div>
        <div class="card-header">
          <a href="<c:url value='/admin/Nrefund'/>" onclick="dashBoard3(event)">미처리 환불 ${RefCount}건이
            있습니다</a>
        </div>
        <div class="card-header">
          <a href="<c:url value='/admin/cs'/>" onclick="dashBoard4(event)">미처리 문의 ${CsCount}건이 있습니다</a>
        </div>
      </div>
   </div>
<script>
    // 판매 데이터 차트 생성
    var ctx = document.getElementById('salesChart').getContext('2d');
    var salesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['오늘 판매액', '이번달 판매액', '올해 판매액', '총 판매액'],
            datasets: [{
                label: '판매액',
                data: [
                    ${salesData.TODAYSALES},
                    ${salesData.MONTHLYSALES},
                    ${salesData.YEARLYSALES},
                    ${salesData.TOTALSALES}
                ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value, index, values) {
                            return value.toLocaleString() + '원';
                        }
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            var label = context.dataset.label || '';
                            if (label) {
                                label += ': ';
                            }
                            if (context.parsed.y !== null) {
                                label += new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(context.parsed.y);
                            }
                            return label;
                        }
                    }
                }
            }
        }
    });

    // 상위 판매 앨범 차트 생성
    var topAlbumsCtx = document.getElementById('topAlbumsChart').getContext('2d');
    var topAlbumsChart = new Chart(topAlbumsCtx, {
        type: 'bar',
        data: {
            labels: [
                <c:forEach items="${topData}" var="album">
                    '${album.ALBUM_ID}',
                </c:forEach>
            ],
            datasets: [{
                label: '판매량',
                data: [
                    <c:forEach items="${topData}" var="album">
                        ${album.SALES_COUNT},
                    </c:forEach>
                ],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: false,
                
                    },
                    ticks: {
                        callback: function(value) {
                            return value.toLocaleString() + '개';
                        }
                    }
                },
                x: {
                    title: {
                        display: false,
                        
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return '판매량: ' + context.parsed.y.toLocaleString() + '개';
                        }
                    }
                }
            }
        }
    });
</script>

    
	<c:import url="/WEB-INF/views/admin/includes/admin_footer.jsp"></c:import> 

</body>
</html>