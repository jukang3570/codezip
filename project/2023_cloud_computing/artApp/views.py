from bs4 import Comment
from django.http import JsonResponse, HttpResponse
from django.shortcuts import get_object_or_404, redirect, render
from .models import Post
from .models import Comment
from .forms import CommentForm
from django.core.mail import EmailMessage

# Create your views here.
def index(request):
    return render(request, 'artApp/index.html')

def project_detail(request):
    return render(request, 'artApp/project-detail.html')

# 좋아요 url
def like1(request):
    post = get_object_or_404(Post, pk=1)
    post.like += 1
    post.save()
    return redirect('exhibition1_1')

def like2(request):
    post = get_object_or_404(Post, pk=2)
    post.like += 1
    post.save()
    return redirect('exhibition1_2')

def like3(request):
    post = get_object_or_404(Post, pk=3)
    post.like += 1
    post.save()
    return redirect('exhibition1_3')

def like4(request):
    post = get_object_or_404(Post, pk=4)
    post.like += 1
    post.save()
    return redirect('exhibition2_1')

def like5(request):
    post = get_object_or_404(Post, pk=5)
    post.like += 1
    post.save()
    return redirect('exhibition2_2')

def like6(request):
    post = get_object_or_404(Post, pk=6)
    post.like += 1
    post.save()
    return redirect('exhibition2_3')

def like7(request):
    post = get_object_or_404(Post, pk=7)
    post.like += 1
    post.save()
    return redirect('exhibition3_1')

def like8(request):
    post = get_object_or_404(Post, pk=8)
    post.like += 1
    post.save()
    return redirect('exhibition3_2')

def like9(request):
    post = get_object_or_404(Post, pk=9)
    post.like += 1
    post.save()
    return redirect('exhibition3_3')

def like10(request):
    post = get_object_or_404(Post, pk=10)
    post.like += 1
    post.save()
    return redirect('exhibition3_4')

#댓글
def comment_create1(request, exhibition):
    commentform = Comment()
    text = request.POST.get('commentText')
    commentform.comment = text
    commentform.exhibition = exhibition
    commentform.save()

    return redirect('exhibition1_4')

def comment_create2(request, exhibition):
    commentform = Comment()
    text = request.POST.get('commentText')
    commentform.comment = text
    commentform.exhibition = exhibition
    commentform.save()

    return redirect('exhibition2_4')

def comment_create3(request, exhibition):
    commentform = Comment()
    text = request.POST.get('commentText')
    commentform.comment = text
    commentform.exhibition = exhibition
    commentform.save()

    return redirect('exhibition3_5')

# exhibition1
def hong0(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition1/enter.html', {'posts': posts})

def hong1(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition1/1page.html', {'posts': posts})

def hong2(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition1/2page.html', {'posts': posts})

def hong3(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition1/3page.html', {'posts': posts})

# 댓글 객체 -> comment_form으로 넘겨줌
def hong4(request):
    posts = Post.objects.all()
    comment_form = Comment.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition1/4page.html', {'posts': posts, 'comment_form':comment_form})


def hong5(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition1/5page.html', {'posts': posts})

# exhibition2
def exhibition2(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition2/enter.html', {'posts': posts})

def exhibition2_1(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition2/1page.html', {'posts': posts})

def exhibition2_2(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition2/2page.html', {'posts': posts})

def exhibition2_3(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition2/3page.html', {'posts': posts})

def exhibition2_4(request):
    posts = Post.objects.all()
    comment_form = Comment.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition2/4page.html', {'posts': posts, 'comment_form':comment_form})


# exhibition3
def exhibition3(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition3/enter.html', {'posts': posts})

def exhibition3_1(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition3/1page.html', {'posts': posts})

def exhibition3_2(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition3/2page.html', {'posts': posts})

def exhibition3_3(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition3/3page.html', {'posts': posts})

def exhibition3_4(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition3/4page.html', {'posts': posts})

def exhibition3_5(request):
    posts = Post.objects.all()
    comment_form = Comment.objects.all()
    print(posts)
    return render(request, 'artApp/exhibition3/5page.html', {'posts': posts, 'comment_form':comment_form})


#홍세연 작가 페이지
def hong_detail(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/artist/artist-page-hong.html', {'posts': posts})

# 홍원표 작가 페이지
def pyo_detail(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/artist/artist-page-pyo.html', {'posts': posts})

# 유근택 작가 페이지
def yoo_detail(request):
    posts = Post.objects.all()
    print(posts)
    return render(request, 'artApp/artist/artist-page-yoo.html', {'posts': posts})

# 이메일 발송
def send_email(request):
    if request.method == 'POST':
        title = request.POST.get('title')
        address = request.POST.get('email')
        message = request.POST.get('message')

        email = EmailMessage(
            title,
            message,
            to=[address]
        )
        try:
            email.send()
            return HttpResponse('이메일이 성공적으로 전송되었습니다.')
        except:
            return HttpResponse('이메일 전송 중 오류가 발생했습니다.')

    return HttpResponse('잘못된 요청입니다.')