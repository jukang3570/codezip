"""
URL configuration for artProj project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path

import artApp.views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', artApp.views.index, name='index'),
    path('home',artApp.views.index, name='index'), 
    
    # 좋아요 증가 url
    path('exhibition1/like/1/', artApp.views.like1, name='like1' ),
    path('exhibition1/like/2/', artApp.views.like2, name='like2' ),
    path('exhibition1/like/3/', artApp.views.like3, name='like3' ),

    path('exhibition2/like/4/', artApp.views.like4, name='like4' ),
    path('exhibition2/like/5/', artApp.views.like5, name='like5' ),
    path('exhibition2/like/6/', artApp.views.like6, name='like6' ),

    path('exhibition3/like/7/', artApp.views.like7, name='like7' ),
    path('exhibition3/like/8/', artApp.views.like8, name='like8' ),
    path('exhibition3/like/9/', artApp.views.like9, name='like9' ),
    path('exhibition3/like/10/', artApp.views.like10, name='like10' ),


    #댓글 추가
    path('exhibition1/comment_create/<int:exhibition>', artApp.views.comment_create1, name='comment_create1'),
    path('exhibition2/comment_create/<int:exhibition>', artApp.views.comment_create2, name='comment_create2'),
    path('exhibition3/comment_create/<int:exhibition>', artApp.views.comment_create3, name='comment_create3'),

    # 전시회 1 (홍세연)
    path('exhibition1/', artApp.views.hong0, name='exhibition1'),
    path('exhibition1/1page', artApp.views.hong1, name='exhibition1_1'),
    path('exhibition1/2page', artApp.views.hong2, name='exhibition1_2'),
    path('exhibition1/3page', artApp.views.hong3, name='exhibition1_3'),
    path('exhibition1/4page', artApp.views.hong4, name='exhibition1_4'),
    
    # 전시회 2
    path('exhibition2/', artApp.views.exhibition2, name='exhibition2'),
    path('exhibition2/1page', artApp.views.exhibition2_1, name='exhibition2_1'),
    path('exhibition2/2page', artApp.views.exhibition2_2, name='exhibition2_2'),
    path('exhibition2/3page', artApp.views.exhibition2_3, name='exhibition2_3'),
    path('exhibition2/4page', artApp.views.exhibition2_4, name='exhibition2_4'),
    
    # 전시회 3
    path('exhibition3/', artApp.views.exhibition3, name='exhibition3'),
    path('exhibition3/1page', artApp.views.exhibition3_1, name='exhibition3_1'),
    path('exhibition3/2page', artApp.views.exhibition3_2, name='exhibition3_2'),
    path('exhibition3/3page', artApp.views.exhibition3_3, name='exhibition3_3'),
    path('exhibition3/4page', artApp.views.exhibition3_4, name='exhibition3_4'),
    path('exhibition3/5page', artApp.views.exhibition3_5, name='exhibition3_5'),

    # 홍세연 작가 페이지
    path('artist-page-hong.html', artApp.views.hong_detail, name='hong_detail'),

    # 홍원표 작가 페이지
    path('artist-page-pyo.html', artApp.views.pyo_detail, name='pyo_detail'),

    # 유근택 작가 페이지
    path('artist-page-yoo.html', artApp.views.yoo_detail,name='yoo_detail'),

    # 이메일보내기
    path('send_email/', artApp.views.send_email, name='send_email'),
]

