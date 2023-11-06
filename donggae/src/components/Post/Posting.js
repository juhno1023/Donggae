import styles from "./Posting.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../_Layout/Header";

export default function Home() {
    const history = useNavigate();

    return (
        <div className={styles.default}>
            <Header />
            <div className={styles.inner}>
                <div className={styles.body}>
                <form>
                    <button type="submit">작성완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                        <label  className={styles.text__1} for="team_name" >팀명</label>
                        <input type="text" id="team_name"
                        placeholder="팀 이름을 작성해주세요." />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg2}`}>
                        <label for="post_title">제목</label>
                        <input type="text" id="post_title"
                        placeholder="제목을 작성해주세요. (예시 : 함께 ㅇㅇ 프로젝트를 이끌어 갈 분들을 모집합니다! )" />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                        <label for="post_content">내용</label>
                        <textarea id="post_content"
                        placeholder="내용을 작성해주세요. (예시 : 이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs, ...)" />
                    </div>
                </form>
                
                <div className={styles.box__}>
                    <div className={styles.half}>
                        <div className={styles.text__1}>세부사항 설정</div>
                        <div className={styles.text__2}>모집 분야</div>
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span class="list">전체</span>
                                </label>
                            </div>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span class="list">JavaScript</span>
                                </label>
                            </div>
                        </div>
                        <div className={styles.text__2}>선호 언어</div>
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span class="list">전체</span>
                                </label>
                            </div>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span class="list">JavaScript</span>
                                </label>
                            </div>
                        </div>
                        <div className={styles.text__2}>선호 성향</div>
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span class="list">전체</span>
                                </label>
                            </div>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span class="list">JavaScript</span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div className={styles.half}>
                    <div className={styles.text__1}>전공강의 팀원 모집하기</div>
                        <div className={styles.text__2}>선택 된 수강강의
                        <select>
                            <option value="Option 1">없음</option>
                            <option value="Option 2">소프트웨어공학개론</option>
                            <option value="Option 3">Option 3</option>
                            <option value="Option 4">Option 4</option>
                            <option value="Option 5">Option 5</option>
                            <option value="Option length">
                            Options
                            </option>
                        </select>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>

    );
}