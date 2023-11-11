import { useNavigate } from "react-router-dom";
import styles from "./Post.module.css"
import Header from "../../components/_Layout/Header";
import bgImg from '../../image/donggae.png';

export default function Post() {
  return (
    <div className={styles.default}>
        <Header />
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                <button type="submit">지원하기</button>
                <div  className={styles.text__1} for="team_name" >🧡떡잎방범대🧡이번 학기에 소공 같이 플젝 하실 분 모집합니다</div>
                <div className={styles.formGroup}>
                    <div>이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs 입니다
                        </div>
                </div>
            </div>
            <div className={styles.box__}>
                <div className={styles.half}>
                <div className={styles.text__1}>팀장 정보</div>
                <div className={styles.profile_box}>
                    <div className={styles.logo}>
                    <img className={styles.logoimg} src={bgImg} alt="Donggae Logo" />
                    <div className={styles.profile_info}>
                        <div className={styles.text__2}>Dabin</div>
                        vhlekqls@naver.com
                        <br></br>
                        웹프로젝트 장인입니다.
                    </div>
                    </div>
                </div>
                <div className={styles.profile_more}>
                    <div className={styles.keyword_box}>
                        <div className={styles.keyword}>
                            모집 분야
                            <span className={styles.list}>JavaScript</span>
                        </div>
                        <div className={styles.keyword}>
                            모집 분야
                            <span className={styles.list}>C++</span>
                        </div>
                    </div>
                    <div className={styles.keyword_box}>
                        <div className={styles.keyword}>
                            모집 분야
                            <span className={styles.list}>성실함</span>
                        </div>
                        <div className={styles.keyword}>
                            모집 분야
                            <span className={styles.list}>냠냠</span>
                        </div>
                    </div>
                </div>
                
                </div>
                    <div className={styles.half}>
                        <div className={styles.text__1}>세부사항 설정</div> 모집 분야
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>전체</span>
                                </label>
                            </div>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>JavaScript</span>
                                </label>
                            </div>
                        </div>
                        선호 언어
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>전체</span>
                                </label>
                            </div>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>JavaScript</span>
                                </label>
                            </div>
                        </div>
                        선호 성향
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>전체</span>
                                </label>
                            </div>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>JavaScript</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
);
}