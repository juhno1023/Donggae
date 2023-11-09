import styles from "./Application.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../_Layout/Header";
import bgImg from '../../image/donggae.png';

export default function Posting() {
    const history = useNavigate();

    return (
        <div className={styles.default}>
            <Header />
            <div className={styles.inner}>
                <div className={styles.body}>
                <form>
                    <button type="submit">작성완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                        <label  className={styles.text__1} for="team_name" >자기소개</label>
                        <input type="text" id="team_name"
                        placeholder="간단한 자기 소개를 작성해주세요." />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                        <label for="post_content">지원동기 및 나의 역량</label>
                        <textarea id="post_content"
                        placeholder="내 기술 스택, 지원 동기, 하고 싶은 이유, 이 프로젝트를 위한 나만의 아이디어, ..." />
                    </div>
                </form>
                
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
                                    <span class="list">JavaScript</span>
                                </div>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span class="list">C++</span>
                                </div>
                            </div>
                            <div className={styles.keyword_box}>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span class="list">성실함</span>
                                </div>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span class="list">냠냠</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>

    );
}