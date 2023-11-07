import { useNavigate } from "react-router-dom";
import "./style.css";
import donggae from '../../image/donggae.png';
import github from '../../image/GitHub.png';

export default function Home() {
    const history = useNavigate();

        return (
          <div className="login-page">
            <div className="div">
              <div className="overlap">
                <div className="group" />
                <div className="text-wrapper">로그인</div>
                <div className="text-wrapper-2">회원가입</div>
                <img className="image" alt="Image" src={github} />
              </div>
              <div className="overlap-group-wrapper">
                <div className="overlap-group">
                  <div className="text-wrapper-3">Donggae</div>
                  <p className="p">
                    <span className="span">동</span>
                    <span className="text-wrapper-4">국대</span>
                    <span className="span"> 개</span>
                    <span className="text-wrapper-4">발자들</span>
                  </p>
                </div>
              </div>
              <img className="img" alt="Image" src={donggae} />
              <div className="text-wrapper-5">프로젝트 팀원을 찾으세요!</div>
            </div>
          </div>
        );
}